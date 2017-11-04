# Desafio Concrete
# MURILO MENDES CARVALHO

API RESTful com Framework Spring Boot

Segue abaixo o lick da aplicação hospedada no Heroku

https://shrouded-fjord-36232.herokuapp.com/

# EndPoints

Cadastro Usuario
POST  JSON

https://shrouded-fjord-36232.herokuapp.com/usuario

{
    "name": "Murilo Mendes",
    "email": "murilomendes@gmail.com",
    "password": "mu1234",
    "phones": [
        {
            "number": "991124345",
            "ddd": "13"
        },
        {
        	"number": "33224565",
            "ddd": "13"
        }
    ]
}
# 

Get Usuario
GET 

https://shrouded-fjord-36232.herokuapp.com/usuario/{id}

Headers
token : "44444-33333-22222-11111"

# 

Login
POST  JSON

https://shrouded-fjord-36232.herokuapp.com/login

{
    "email": "murilomendes@gmail.com",
    "password": "mu1234"
}

# 
