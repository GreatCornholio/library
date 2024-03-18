
### !!! IMPORTANT !!!: 
### Made with Java 21 and Spring boot 3. Maybe will work on Java 17, and probably not on lower versions.
### Developed with intellij on Windows (but, high probability that could start on Linux/Mac too)


#### a) returns all users who have actually borrowed at least one book
'curl http://localhost:8080/reports/users-who-borrowed-at-least-one-book'
#### b) returns all non-terminated users who have not currently borrowed anything
'curl http://localhost:8080/reports/non-terminated-users-who-have-not-currently-borrowed-anything'

with current dataset, result is empty

#### c) returns all users who have borrowed a book on a given date
'curl http://localhost:8080/reports/users-who-have-borrowed-book-on-a-date?date=2021-05-10'
#### d) returns all books borrowed by a given user in a given date range
'curl "http://localhost:8080/reports/books-borrowed-by-user-by-date-range?user=Aexi,Liam&date_from=2015-05-09&date_to=2020-01-01"  '
#### e) returns all available (not borrowed) books
'curl http://localhost:8080/reports/all-available-books'

