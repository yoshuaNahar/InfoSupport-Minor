Oplevering
==========

## Install

Onze docker-compose.yml file zit in ./env-production.
Om de application te runnen moet deze command worden
uitgevoerd in bovenstaande directory: `docker-compose up`
 
## Documentatie

Onze documentatie zit in de folder ../docs.
Hier zit de projectplanning en sprintdocumentatie.

Onze screenshots zit in de ../docs/screenshots folder.

## Project

Het project is onderverdeeld in 5 modules:

1. Web
2. Vehicle
3. Customer
4. Maintenance
5. Rdw

<hr>

* Web communiceert uitsluitend met Vehicle, Maintenance en Customer.<br>
* Vehicle en Customer zijn crud services met een MySQL database erachter.<br>
* Maintenance is een crud service met MySQL database voor het opslaan van alle RDW Request en 
response objects. Daarnaast communiceert het via RabbitMQ met de Rdw service.<br>
* Rdw is de service die met de Rdw communiceert.

