# library-management-system

## How To Run

To start the service run:
`docker-compose up --build`

After all the services start we need to run the python script that will populate our db.
I created a variable for each table that represents the number of rows the script should add, you can change the numbers as you like in the main function. I didn't use environment variables for simplicity ***write once run many times***.
To run the python script we should run:
`docker exec -it python_scripts python generate_fake_data.py`

you can now interact with the API using any client like Postman

All the endpoints aren't secured except adding a new user. In order to add user use the following settings. I created two in mermory users, one with role admin which can access anything and the other with role patron which can do anything except adding users

![image](https://github.com/user-attachments/assets/0279945f-cdb0-467f-85b1-1e9ab80a20ed)
