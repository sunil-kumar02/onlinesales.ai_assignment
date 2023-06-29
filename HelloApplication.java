/*Task-1 SQL
In the attachment below, use each worksheet as a table in a relational database
and write an SQL query that generates the output report
*/
SELECT d.NAME AS DEPT_NAME, AVG(s.AMT) AS AVG_MONTHLY_SALARY
        FROM Departments d
        JOIN Employees e ON d.ID = e.DEPT_ID
        JOIN Salaries s ON e.ID = s.EMP_ID
        GROUP BY d.ID, d.NAME
        ORDER BY AVG_MONTHLY_SALARY DESC
        LIMIT 3;

/*Task-2 Scripting
With the same attachment from Task-1, use each worksheet as a CSV file and
write a script (Bash or Python) that generates the same report.
Data is to be read from the CSV files not from a database.
 */



//Read department data from CSV
        import csv
        with open('departments.csv', 'r') as file:
               departments_data = list(csv.reader(file))

               // Read employee data from CSV
               with open('employees.csv', 'r') as file:
               employees_data = list(csv.reader(file))

               // Read salary data from CSV
               with open('salaries.csv', 'r') as file:
               salaries_data = list(csv.reader(file))

               // Calculate average monthly salary per department
               department_salaries = {}
               for employee in employees_data[1:]:
               dept_id = int(employee[2])
               emp_id = int(employee[0])
               monthly_salaries = [int(salary[2]) for salary in salaries_data if int(salary[0]) == emp_id]
               average_salary = sum(monthly_salaries) / len(monthly_salaries)
               if dept_id in department_salaries:
               department_salaries[dept_id].append(average_salary)
               else:
               department_salaries[dept_id] = [average_salary]

               // Calculate overall average monthly salary per department
               department_avg_salaries = {}
               for dept in department_salaries:
               department_avg_salaries[dept] = sum(department_salaries[dept]) / len(department_salaries[dept])

               // Get top 3 departments with highest average salary
               top_departments = sorted(department_avg_salaries.items(), key=lambda x: x[1], reverse=True)[:3]

               // Print the report
               print("DEPT_NAME    AVG_MONTHLY_SALARY (USD)")
               for dept_id, avg_salary in top_departments:
               dept_name = [dept[1] for dept in departments_data if int(dept[0]) == dept_id][0]
               print(f"{dept_name}    {avg_salary:.2f}")




/*Task-3 Debugging
Given below is a Bash / Python script that performs following computation on an integer input (n):
If n is less than 10: Calculate its Square
Example: 4 => 16
If n is between 10 and 20: Calculate the factorial of (n-10)
Example: 15 => 120
If n is greater than 20: Calculate the sum of all integers between 1 and (n-20)
Example: 25 => 15
 */

        def compute(n):
        if n < 10: //for range 0 to 9
        out = n ** 2
        elif n < 20: //If n is between 10 and 20
        out = 1
        for i in range(1, n-9):  // Fixed: Changed n-10 to n-9
        out *= i
        else:
        lim = n - 20
        out = 0  // Fixed: Changed lim * lim to 0
        for i in range(1, lim+1):  // Fixed: Added +1 to lim
        out += i
        print(out)


        n = int(input("Enter an integer: "))
        compute(n)


/*Task-4 Bash*/

grep '^Xerox' sample.tsv | awk -F '\t' '{print $1"\t"$0}' | awk -F '\t' -v OFS='\t' '{print $2, NR-1}' | nl -w1 -s $'\t' - > output.tsv; cat output.tsv | tee >(wc -l) | tee >(sha1sum) | tail -5

/*1. grep '^Xerox' sample.tsv: Filters the lines from the sample.tsv file that start with 'Xerox'.
2. awk -F '\t' '{print $1"\t"$0}': Adds the first column (Item Name) as an additional column at the beginning of each line.
3. awk -F '\t' -v OFS='\t' '{print $2, NR-1}': Adds an additional column at the end containing the line number of the item from the original file.
4. nl -w1 -s $'\t' -: Adds line numbers to each line with a tab as the separator.
5. > output.tsv: Redirects the output to the file output.tsv.
 */

/*Task-5 Bash
 */

        ls -l | awk '{print $3}' | sort | uniq | xargs -I {} id -ng {}


/*

1. ls -l: Lists the files in the current directory in long format.
2. awk '{print $3}': Extracts the owner name (3rd column) from the output of ls -l.
3. sort: Sorts the owner names in alphabetical order.
4. uniq: Filters out duplicate owner names.
5. xargs -I {} id -ng {}: Passes each unique owner name as an argument to id -ng command, which retrieves the group name of the owner.

 */