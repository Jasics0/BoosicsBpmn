# BoosicsBpmn

BoosicsBpmn is a Spring Boot project that provides functionalities for user registration and email sending. This application runs on port 8090 and requires various dependencies and configurations for its proper functioning.

## Prerequisites

Before running BoosicsBpmn, make sure you have the following installed:

- Java Development Kit (JDK) 8 or higher
- Maven
- A properly configured email server (e.g., SMTP)

## Configuration

Follow these steps to set up and run BoosicsBpmn:

1. Clone the BoosicsBpmn repository:

  - git clone https://github.com/Jasics0/BoosicsBpmn

2. Navigate to the project directory:

  - cd BoosicsBpmn
    
3. Set database configuration:

  - Check file application.properties and set your configuraton

![image](https://github.com/Jasics0/BoosicsBpmn/assets/61464947/1700125d-c0f9-455f-9f4e-39661576aa1d)

4. Set camunda configuration:

  - Check file application.properties and set your configuraton

![image](https://github.com/Jasics0/BoosicsBpmn/assets/61464947/959c0540-e5c1-49a5-925a-703428f19ba3)

5. To configure the smtp server you have to create a password for applications (This depends on the server), in gmail you can do it in the following way:

 - Activate the two-step configuration:

  ![image](https://github.com/Jasics0/BoosicsBpmn/assets/61464947/6e791495-5a3d-48b2-9d7e-38c42b43b0b1)

- In the same section select "Passwords and applications":

![image](https://github.com/Jasics0/BoosicsBpmn/assets/61464947/7174dc36-922d-416c-99cc-860a64cba1d0)

- Create password:

![image](https://github.com/Jasics0/BoosicsBpmn/assets/61464947/6a56dce9-3234-4c34-abb7-8acf85caa98a)

- Finally, copy and paste the password in the mail.sender.pass attribute of the application.properties.

![image](https://github.com/Jasics0/BoosicsBpmn/assets/61464947/7c9a05bb-d4a4-44f3-a558-db3c1e5f343c)

-You can also change the host and smtp port in the application.properties

## Usage

Once BoosicsBpmn is running, you can access the user interface through your web browser at the following URL:

http://localhost:8090


The user interface provides the following functionalities:

- User Registration: Fill out the registration form with the required information and click "Register" to create a new user account.

## Contributions

If you wish to contribute to the development of BoosicsBpmn, follow these steps:

1. Fork this repository.
2. Create a new branch for your contribution.


