self.addEventListener("install", () => {
  event.waitUntil(
      caches.open("johncache").then(function (cache) {
        return cache.addAll(
            [
              '/todo.html'
            ]
        );
      })
  );
});

self.addEventListener('fetch', function (event) {
  event.respondWith(
      caches.open('johncache').then(function (cache) {
        return cache.match(event.request).then(function (response) {
          return response || fetch(event.request).then(function (response) {
            cache.put(event.request, response.clone());
            return response;
          });
        });
      })
  );
});
