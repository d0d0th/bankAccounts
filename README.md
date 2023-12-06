#  bankAccounts API
This system is a REST API that allows for customer registration, bank account inclusion, and the execution of deposits and withdrawals in the accounts.

## Getting Started
This document contains the necessary instructions for execute and test the application in a docker container locally.

### Prerequisites

Requirements for the software and other tools to build, test and push
- [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven](https://maven.apache.org/)
- [Docker](https://docs.docker.com/get-docker/)

### Installing

Clone the project repository
```shell
user@local:~$ git clone https://github.com/d0d0th/bankAccounts.git
```   

Navigate to the project directory and execute the Maven command to generate the JAR package.     
```bash
user@local:~$ cd bankAccounts/
user@local:~$ mvn clean package -DskipTests
```

Build the Docker image

```bash
user@local:~$ docker build -t app-spring .
```    

Run the application using Docker Compose
```bash
user@local:~$ docker-compose up
```

From this point on, the API definition can be accessed at the following URL:

http://localhost:8080/swagger-ui/index.html


## Testing the API
 
### Create a Customer

An example of how to create a customer using their ID and name:

    curl --location 'localhost:8080/api/v1/customers' \
    --header 'Content-Type: application/json' \
    --data '{
    "id": 123456,
    "name": "Lucas Lima"
    }'


### Create an Account for the Customer

To create a new account for the customer, it is necessary to provide an initial value for the account:

    curl --location 'localhost:8080/api/v1/customers/1/accounts' \
    --header 'Content-Type: application/json' \
    --data '{
    "amount" :100
    }'


### Make a Deposit

Example of how to make a deposit into the created account:

    curl --location 'localhost:8080/api/v1/accounts/1/transactions?type=DEPOSIT' \
    --header 'Content-Type: application/json' \
    --data '{
    "amount" : 2
    }'

### Make a Withdrawal

Example of how to make a withdrawal from the account:

    curl --location 'localhost:8080/api/v1/accounts/1/transactions?type=WITHDRAW' \
    --header 'Content-Type: application/json' \
    --data '{
    "amount" : 2
    }'

### Get Transactions

Example of how to retrieve transactions from the account within a time interval:

    curl --location 'http://localhost:8080/api/v1/accounts/1/transactions?startDateTime=2023-12-02T08%3A00%3A00&endDateTime=2023-12-03T12%3A47%3A00'

