## Mappers
The mappers are responsible for converting DTOs to Entities and the other way around.
If entity and DTO variable names are identical they don't need to be specified in mapping.

If something seems off between the in/output that reaches/comes from the database, 
check if items are getting mapped appropriately.

After changing any of the mappers it is important to run ```mvn clean install``` to ensure proper implementation.
