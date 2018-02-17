# language: nl
Functionaliteit: Factuur Maken

  Scenario: Een medewerker secretariaat moet een
    week kunnen selecteren om een factuur voor een
    specifieke cursist op te kunnen stellen.

    Gegeven een medewerker die een week selecteert
    Als er cursisten zijn binnen die week die een cursus hebben gevolgd
    Dan krijgt de medewerker een lege factuur die hij kan invullen voor de cursisten

  Scenario: Een medewerker kan voor iedere week de
    cursussen zien die een cursist heeft gevolgd en de
    bankgegevens van de cursist bekijken.

    Gegeven een medewerker die een cursist kan selecteren in een week
    Als een cursist een cursus heeft gevold voor de week
    Dan krijgt de medewerker de bankgegevens van de cursist te zien
