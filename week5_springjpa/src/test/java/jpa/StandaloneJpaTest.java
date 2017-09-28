package jpa;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintViolationException;
import jpa.domain.Author;
import jpa.domain.Book;
import jpa.domain.BookCategory;
import jpa.domain.BookDetails;
import jpa.domain.EBook;
import jpa.domain.EBookFormat;
import jpa.domain.Promotion;
import jpa.domain.Review;
import jpa.domain.view.Rating;
import org.hibernate.LazyInitializationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StandaloneJpaTest {

  private EntityManagerFactory emf;
  private EntityManager em;

  private void createTestBooks() {
    em.getTransaction().begin();
    em.persist(new Book("Pro JPA 2", LocalDate.of(2013, 5, 25), BookCategory.JPA, null));
    em.persist(
        new Book("Spring in Action 1", LocalDate.of(2005, 8, 19), BookCategory.SPRING, null));
    em.persist(new Book("Spring in Action 2", LocalDate.of(2008, 7, 7), BookCategory.SPRING, null));
    em.persist(new Book("Spring in Action 3", LocalDate.of(2011, 8, 9), BookCategory.SPRING, null));
    em.persist(
        new Book("Spring in Action 4", LocalDate.of(2014, 4, 17), BookCategory.SPRING, null));
    em.getTransaction().commit();

    em.close();
    em = emf.createEntityManager();
  }

  @Before
  public void setup() {
    emf = Persistence.createEntityManagerFactory("jpa-pu");
    em = emf.createEntityManager();
    createTestBooks();
  }

  @After
  public void breakDown() {
    if (em.isOpen()) {
      em.close();
    }
    if (emf.isOpen()) {
      emf.close();
    }
  }

  @Test
  public void testEntityManager() {
    assertThat(em, is(notNullValue()));
  }

  @Test
  public void testFind() {
    Book book = em.find(Book.class, 1L);

    assertThat(book, is(notNullValue()));
    assertThat(book.getTitle(), is("Pro JPA 2"));
  }

  @Test
  public void testManagedEdit() {
    Book book = em.find(Book.class, 1L);
    em.getTransaction().begin();
    book.setTitle("Updated");
    em.getTransaction().commit();
    em.close();

    em = emf.createEntityManager();
    book = em.find(Book.class, 1L);

    assertThat(book.getTitle(), is("Updated"));
  }

  @Test
  public void testMerge() {
    Book book = em.find(Book.class, 1L);
    em.close();
    em = emf.createEntityManager();

    book.setTitle("Updated");
    Book dbBook = em.find(Book.class, 1L);

    assertThat(dbBook.getTitle(), is(not("Updated")));

    em.merge(book);
    em.getTransaction().begin();
    em.getTransaction().commit();
    em.close();
    em = emf.createEntityManager();

    dbBook = em.find(Book.class, 1L);

    assertThat(dbBook.getTitle(), is("Updated"));
  }

  @Test
  public void testQuery() {
    TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b", Book.class);
    List<Book> books = query.getResultList();

    assertThat(books.size(), is(5));
  }

  @Test
  public void testRemove() {
    Book book = em.find(Book.class, 2L);

    EntityTransaction entityTransaction = em.getTransaction();
    entityTransaction.begin();
    em.remove(book);
    entityTransaction.commit();

    em.close();
    em = emf.createEntityManager();

    assertThat(countBooks(), is(4L));
  }

  @Test
  public void testOneToOne() {
    Book book = em.find(Book.class, 1L);
    em.getTransaction().begin();
    book.setPromotion(new Promotion("Test promotion", new BigDecimal(10), LocalDate.of(2016, 5, 23),
        LocalDate.of(2016, 5, 27)));
    em.getTransaction().commit();

    em.close();
    em = emf.createEntityManager();
    book = em.find(Book.class, 1L);

    assertThat(book.getPromotion(), is(notNullValue()));
  }

  @Test
  public void testOneToOneBidirectional() {
    Book book = em.find(Book.class, 1L);
    em.getTransaction().begin();
    Promotion promotion = new Promotion("Test promotion", new BigDecimal(10),
        LocalDate.of(2016, 5, 23),
        LocalDate.of(2016, 5, 27));
    promotion.setBook(book);
    em.persist(promotion);
    em.getTransaction().commit();

    em.close();
    em = emf.createEntityManager();
    book = em.find(Book.class, 1L);

    assertThat(book.getPromotion(), is(notNullValue()));
  }

  @Test
  public void testOneToOneCascade() {
    // Test if both the book and promotion are persisted. Answer: Only with
    // the CascadeType.PERSIST on the @OneToOne relation at the Book entity
    Book book = em.find(Book.class, 1L);

    Promotion promotion = new Promotion("Sale", new BigDecimal(25.0), LocalDate.of(2016, 5, 23),
        LocalDate.of(2016, 5, 27));

    book.setPromotion(promotion);

    EntityTransaction entityTransaction = em.getTransaction();
    entityTransaction.begin();

    em.persist(book);

    entityTransaction.commit();

    em.close();
    em = emf.createEntityManager();

    // Now we try to remove this book, will the promotion be deleted as
    // well? Answer: Only when CascadeType.REMOVE is added to the @OneToOne
    // relation on the Book entity
    Book managedBook = em.find(Book.class, 1L);

    entityTransaction = em.getTransaction();
    entityTransaction.begin();

    em.remove(managedBook);

    entityTransaction.commit();

    em.close();
    em = emf.createEntityManager();

    assertThat(countPromotions(), is(0L));
    assertThat(countBooks(), is(4L));
  }

  @Test
  public void testOneToManyPersist() {
    // We create  2 reviews that are aware of the Book, than we
    // add the list of reviews to the book and persist it
    Book book = em.find(Book.class, 1L);
    List<Review> reviews = new ArrayList<>();
    reviews.add(new Review("Felix", LocalDate.of(2016, 5, 25), 8, "Very useful examples.", book));
    reviews.add(new Review("JP", LocalDate.of(2016, 5, 25), 7, "Too many pages.", book));

    book.setReviews(reviews);

    EntityTransaction entityTransaction = em.getTransaction();
    entityTransaction.begin();

    em.persist(book);

    entityTransaction.commit();

    em.close();
    em = emf.createEntityManager();

    book = em.find(Book.class, 1L);
    assertThat(book.getReviews().size(), is(2));
  }

  @Test
  public void testOneToManyCascadeRemove() {
    // Try to delete one review, in order to do this we need to "sync" the
    // managed book. This can be done by removing the review from the book.
    Book book = em.find(Book.class, 1L);
    List<Review> reviews = new ArrayList<>();
    reviews.add(new Review("Felix", LocalDate.of(2016, 5, 25), 8, "Very useful examples.", book));
    reviews.add(new Review("JP", LocalDate.of(2016, 5, 25), 7, "Too many pages.", book));

    book.setReviews(reviews);

    em.getTransaction().begin();
    em.persist(book);
    em.getTransaction().commit();

    em.close();
    em = emf.createEntityManager();

    Review managedReview1 = em.find(Review.class, 1L);
    Review managedReview2 = em.find(Review.class, 2L);

    managedReview1.getBook().getReviews().remove(managedReview1);
    managedReview2.getBook().getReviews().remove(managedReview2);
    System.out.println("Is the book managed? " + em.contains(book));

    em.getTransaction().begin();
    //Try to remove one review when CascadeType.REMOVE is specified
    em.remove(managedReview1);
    em.remove(managedReview2);
    em.getTransaction().commit();

    em.close();
    em = emf.createEntityManager();

    assertThat(countReviews(), is(0L));
    assertThat(countBooks(), is(4L));
  }

  @Test(expected = LazyInitializationException.class)
  public void testManyToOneLazyLoading() {
    Book book = em.find(Book.class, 1L);
    em.getTransaction().begin();
    Review review = new Review("Felix", LocalDate.of(2016, 5, 25), 8, "Very useful examples.",
        book);
    book.getReviews().add(review);
    em.getTransaction().commit();
    em.close();

    em = emf.createEntityManager();
    book = em.find(Book.class, 1L);
    em.close();

    System.out.println(book.getReviews().size());
  }

  @Test
  public void testManyToMany() {
    createTestBooksWithAuthors();

    String jpqlString = " SELECT b FROM Book b" +
        " WHERE b.title IN ('Pro JPA 1','Spring in Action 5')";
    TypedQuery<Book> query = em.createQuery(jpqlString, Book.class);

    List<Book> books = query.getResultList();

    assertThat(books, is(notNullValue()));
    assertThat(books.get(0).getAuthors().get(0).getName(), is("Mike Keith"));
    assertThat(books.get(0).getAuthors().get(1).getName(), is("Merrick Schincariol"));
    assertThat(books.get(1).getAuthors().get(0).getName(), is("Craig Walls"));
  }

  private void createTestBooksWithAuthors() {
    Book book1 = new Book("Pro JPA 1", LocalDate.of(2010, 5, 25), BookCategory.JPA, null);
    book1.addAuthor(new Author("Mike Keith"));
    book1.addAuthor(new Author("Merrick Schincariol"));

    Book book2 = new Book("Spring in Action 5", LocalDate.of(2018, 4, 17), BookCategory.SPRING,
        null);
    book2.addAuthor(new Author("Craig Walls"));

    em.getTransaction().begin();
    em.persist(book1);
    em.persist(book2);
    em.getTransaction().commit();

    em.close();
    em = emf.createEntityManager();
  }

  @Test
  public void testEBookPersistence() {
    EBook eBook = new EBook("Pro JPA 1", LocalDate.of(2010, 5, 25), BookCategory.JPA, null);

    Set<EBookFormat> formats = new HashSet<>();
    formats.add(EBookFormat.EPUB);
    formats.add(EBookFormat.PDF);
    eBook.setFormats(formats);

    em.getTransaction().begin();
    em.persist(eBook);
    em.getTransaction().commit();
    em.close();
    em = emf.createEntityManager();

    EBook book = em.find(EBook.class, 6L);

    assertThat(book.getFormats().size(), is(2));
  }

  @Test
  public void testInheritanceAndElementCollection() {
    EBook eBook = new EBook("Pro JPA 0", LocalDate.of(2005, 5, 25), BookCategory.JPA, null);
    eBook.setFormats(EnumSet.allOf(EBookFormat.class));

    EntityTransaction entityTransaction = em.getTransaction();
    entityTransaction.begin();

    em.persist(eBook);

    entityTransaction.commit();

    em.close();
    em = emf.createEntityManager();
  }

  @Test
  public void testEmbeddable() {
    Book book = new Book("Pro JPA 4", LocalDate.of(2023, 5, 25), BookCategory.JPA, null);
    book.setDetails(new BookDetails(486, "-1", "-1", "English"));

    EntityTransaction entityTransaction = em.getTransaction();
    entityTransaction.begin();

    em.persist(book);

    entityTransaction.commit();

    em.close();
    em = emf.createEntityManager();
  }

  @Test
  public void testQueryBooksMoreThanOneAuthor() {
    createTestBooksWithAuthors();

    TypedQuery<Book> query = em
        .createQuery("SELECT b FROM Book b WHERE SIZE(b.authors) > 1", Book.class);

    List<Book> books = query.getResultList();

    assertThat(books, is(notNullValue()));
    assertThat(books.size(), is(1));
    assertThat(books.get(0).getTitle(), is("Pro JPA 1"));
  }

  @Test
  public void testAvgRatingQuery() {
    em.getTransaction().begin();

    for (long i = 1; i <= 5; i++) {
      Book book = em.find(Book.class, i);
      book.addAuthor(new Author("author" + i));

      for (int reviewNr = 1; reviewNr <= 10; reviewNr++) {
        //reviewNr acts also as rating
        em.persist(new Review("Paul", LocalDate.of(2016, 5, 25), reviewNr, "Cool book", book));
      }
    }

    em.getTransaction().commit();
    TypedQuery<Rating> query = em.createQuery(
        "SELECT new jpa.domain.view.Rating(author.name, " +
            "(SELECT AVG(r.rating) FROM Review r JOIN r.book.authors a WHERE a.id = author.id)" +
            ") " +
            "FROM Author author", Rating.class);

    List<Rating> resultList = query.getResultList();

    assertThat(resultList.size(), is(5));
    assertThat(resultList.get(0).getAvgRating(), is(5.5));
  }

  @Test
  public void testDynamicFilter() {
    em.getTransaction().begin();
    Book book1 = em.find(Book.class, 1L);
    Book book2 = em.find(Book.class, 2L);

    Author author = new Author("Mike Keith");
    em.persist(author);
    book1.addAuthor(author);
    book2.addAuthor(author);
    em.getTransaction().commit();

    em.close();
    em = emf.createEntityManager();

    assertThat(filterBooks(null, null).size(), is(5));
    assertThat(filterBooks(null, "Mike Keith").size(), is(2));
    assertThat(filterBooks("PRO", "Mike Keith").size(), is(1));
  }

  private List<Book> filterBooks(String title, String authorName) {
    CriteriaBuilder cBuilder = em.getCriteriaBuilder();
    CriteriaQuery<Book> query = cBuilder.createQuery(Book.class);
    Root<Book> book = query.from(Book.class);
    Join<Book, Author> join = book.join("authors", JoinType.LEFT);
    query.select(book).orderBy(cBuilder.asc(book.<String>get("title")));

    Predicate predicate = cBuilder.conjunction();

    if (title != null) {
      predicate = cBuilder.and(predicate, cBuilder.like(book.get("title"), title + "%"));
    }

    if (authorName != null) {
      predicate = cBuilder.and(predicate, cBuilder.like(join.get("name"), authorName + "%"));
    }

    query.where(predicate);

    return em.createQuery(query).getResultList();
  }

  @Test(expected = ConstraintViolationException.class)
  public void testNameValidation() {
    Book book = new Book();
    em.getTransaction().begin();
    em.persist(book);
    em.flush(); // dit doe je wanneer je in de transaction wilt zien wat er is gebeurt op de database
    em.getTransaction().commit();
  }

  @Test(expected = ConstraintViolationException.class)
  public void testCustomValidation() {
    Book book = new Book("This book is about sex, drugs and Rock & Roll", LocalDate.of(2023, 5, 25),
        BookCategory.JPA, null);
    em.getTransaction().begin();
    em.persist(book);
    em.getTransaction().commit();
  }

  private long countReviews() {
    return em.createQuery("SELECT COUNT(r) FROM Review r", Long.class).getSingleResult();
  }

  private long countPromotions() {
    return em.createQuery("SELECT COUNT(p) FROM Promotion p", Long.class).getSingleResult();
  }

  private long countBooks() {
    return em.createQuery("SELECT COUNT(b) FROM Book b", Long.class).getSingleResult();
  }

}
