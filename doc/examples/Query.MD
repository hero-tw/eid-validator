To query for matches, send a partial identity to 

```
POST /eid
```

```json
{
    "name": {
      "givenName": "Martin",
      "surname": "King"
    },
    "dateOfBirth": "1929-01-15"
}
```