## Models
Responsible for processing all input before it gets to the database.

Note: no deletes for employee or customer, 
those cannot be fully removed from the db. 
Instead, they need to be anonymized if removed.
This is to preserve appointment data
