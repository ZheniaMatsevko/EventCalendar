# Sport Events Calendar

Sport Events Calendar is a web application built with Spring Boot and Thymeleaf for the backend, focusing on managing sport events, teams and kinds of sport. 
The application features a simple frontend for user interaction.

## Getting Started

### Prerequisites

Before you begin, ensure you have met the following requirements:

- Java Development Kit (JDK) installed
- Maven installed
- MySQL database installed and running

### Installation

1. Clone the repository:
git clone https://github.com/ZheniaMatsevko/sport-events-calendar.git

2. Navigate to the project directory:
cd sport-events-calendar

3. Update the application.properties file with your database configuration:
spring.datasource.url=jdbc:mysql://localhost:3306/sport_events_db
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password

4. Build the project:
mvn clean install

5. Run the application:
java -jar target/EventCalendar-0.0.1-SNAPSHOT.jar
The frontend part of application should now be running at http://localhost:8080/all-events.

### Usage

Access the application by navigating to http://localhost:8080 in your web browser.
Explore the Sport Events Calendar and use the provided features to manage sport events, teams and kinds of sport.

### Features

 - Create, update, delete, filter sport events;
 - Manage teams associated with each event.
 - Manage kinds of sport.
 - View a calendar of upcoming sport events.

