## Entities
These entities function as a framework for Spring boot to construct datatables off. 
This allows for automated construction of these tables and for interaction between entities.

### Foreign keys
Entity-relationships are marked in code through the ```@ManyToOne```/```@OneToMany``` and ```@JoinColumn``` annotations.
The ```@JoinColumn``` marks columns where the entity connects to a foreign entity.

The foreign entity recognises this connection through ```@JoinColum(mappedBy=)``` annotation.

An important aspect of these connections are the ```@JsonManagedReference``` and ```@JsonBackReference``` annotations. 
These are necessary to prevent a recursion loop as the two entities will be joined repeatedly.

