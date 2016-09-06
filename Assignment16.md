1)
mysql> select first_name, last_name from student;
+------------+-----------+
| first_name | last_name |
+------------+-----------+
| Eric       | Ephram    |
| Greg       | Gould     |
| Adam       | Ant       |
| Howard     | Hess      |
| Charles    | Caldwell  |
| James      | Joyce     |
| Doug       | Dumas     |
| Kevin      | Kraft     |
| Frank      | Fountain  |
| Brian      | Biggs     |
+------------+-----------+
10 rows in set (0.00 sec)

2)
mysql> select * from student where years_of_experience < 8;
+-----+------------+-----------+---------------------+------------+
| id  | first_name | last_name | years_of_experience | start_date |
+-----+------------+-----------+---------------------+------------+
| 100 | Eric       | Ephram    |                   2 | 2016-03-31 |
| 110 | Greg       | Gould     |                   6 | 2016-09-30 |
| 120 | Adam       | Ant       |                   5 | 2016-06-02 |
| 130 | Howard     | Hess      |                   1 | 2016-02-28 |
| 140 | Charles    | Caldwell  |                   7 | 2016-05-07 |
| 170 | Kevin      | Kraft     |                   3 | 2016-04-15 |
| 190 | Brian      | Biggs     |                   4 | 2015-12-25 |
+-----+------------+-----------+---------------------+------------+
7 rows in set (0.00 sec)

3)
mysql> select last_name, start_date, id from student order by last_name asc;
+-----------+------------+-----+
| last_name | start_date | id  |
+-----------+------------+-----+
| Ant       | 2016-06-02 | 120 |
| Biggs     | 2015-12-25 | 190 |
| Caldwell  | 2016-05-07 | 140 |
| Dumas     | 2016-07-04 | 160 |
| Ephram    | 2016-03-31 | 100 |
| Fountain  | 2016-01-31 | 180 |
| Gould     | 2016-09-30 | 110 |
| Hess      | 2016-02-28 | 130 |
| Joyce     | 2016-08-27 | 150 |
| Kraft     | 2016-04-15 | 170 |
+-----------+------------+-----+
10 rows in set (0.00 sec)

4)
mysql> select * from student where first_name = 'James' or first_name = 'Adam' or first_name = 'Frank' order by last_name desc;
+-----+------------+-----------+---------------------+------------+
| id  | first_name | last_name | years_of_experience | start_date |
+-----+------------+-----------+---------------------+------------+
| 150 | James      | Joyce     |                   9 | 2016-08-27 |
| 180 | Frank      | Fountain  |                   8 | 2016-01-31 |
| 120 | Adam       | Ant       |                   5 | 2016-06-02 |
+-----+------------+-----------+---------------------+------------+
3 rows in set (0.00 sec)

5)
mysql> select first_name, last_name, start_date from student where start_date >= '2016-01-01' and start_date <= '2016-6-30' order by start_date desc;
+------------+-----------+------------+
| first_name | last_name | start_date |
+------------+-----------+------------+
| Adam       | Ant       | 2016-06-02 |
| Charles    | Caldwell  | 2016-05-07 |
| Kevin      | Kraft     | 2016-04-15 |
| Eric       | Ephram    | 2016-03-31 |
| Howard     | Hess      | 2016-02-28 |
| Frank      | Fountain  | 2016-01-31 |
+------------+-----------+------------+
6 rows in set (0.00 sec)

Advanced)
mysql> explain assignment;
+----------------+---------+------+-----+---------+-------+
| Field          | Type    | Null | Key | Default | Extra |
+----------------+---------+------+-----+---------+-------+
| id             | int(11) | NO   | PRI | NULL    |       |
| student_id     | int(11) | NO   | MUL | NULL    |       |
| assignment_nbr | int(11) | NO   |     | NULL    |       |
| grade_id       | int(11) | YES  | MUL | NULL    |       |
+----------------+---------+------+-----+---------+-------+
4 rows in set (0.00 sec)

mysql> select * from assignment;
+-----+------------+----------------+----------+
| id  | student_id | assignment_nbr | grade_id |
+-----+------------+----------------+----------+
| 100 |        100 |              1 |        3 |
| 101 |        110 |              1 |        3 |
| 102 |        120 |              1 |        2 |
| 103 |        130 |              1 |        0 |
| 104 |        140 |              1 |        4 |
| 105 |        150 |              1 |        2 |
| 106 |        160 |              1 |        2 |
| 107 |        170 |              1 |        1 |
| 108 |        180 |              1 |        3 |
| 109 |        190 |              1 |        3 |
+-----+------------+----------------+----------+
10 rows in set (0.00 sec)

mysql> explain grade;
+-------------+--------------+------+-----+---------+-------+
| Field       | Type         | Null | Key | Default | Extra |
+-------------+--------------+------+-----+---------+-------+
| id          | int(11)      | NO   | PRI | NULL    |       |
| description | varchar(127) | YES  |     | NULL    |       |
+-------------+--------------+------+-----+---------+-------+
2 rows in set (0.00 sec)

mysql> select * from grade;
+----+-----------------------------+
| id | description                 |
+----+-----------------------------+
|  0 | Incomplete                  |
|  1 | Complete amd unsatisfactory |
|  2 | Complete amd satisfactory   |
|  3 | Exceeds expectations        |
|  4 | Not graded                  |
+----+-----------------------------+
5 rows in set (0.00 sec)