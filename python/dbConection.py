import mysql.connector

cnx = mysql.connector.connect(user='root', password='root',
                              host='127.0.0.1',
                              database='tms2')
cursor =cnx.cursor()

s =("SELECT * FROM courses")
cursor.execute(s)
for row in cursor:
    print(row)

cnx.close()
print("sucess")
