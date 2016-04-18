

1. Building
  Run mvn commands in the multiproject directory called BileteParcare.Now you can execute group commands, for example:
		>mvn compile
		>mvn install
		>mvn site

	These commands perform operations for each module sequentially.

2. Starting svn repository server

       svnserve -d -r G:\opt\projects\svn --listen-port 3307

3. Create a nightly snapshot :

      D:\disertatie>svn copy svn://127.0.0.1:3307/repository/trunk/BileteParcare svn://127.0.0.1:3307/repository/tags/release-0.0.1-20090511-snapshot -m "Created project snapshot"       

4. Deploy backend :
   remove servlet api, geronimo and jetty from war.
   
5. Balancer : http://127.0.0.1:3030/bileteparcare-backend/services/BileteOnline?wsdl

6. Report for active campaigns :

http://127.0.0.1:9090/bileteparcare-admin/report/active-campaigns

7. Mutarea din reservation in reservation_history :

insert into reservation_history select * from reservation where NVL(canceled,'N') = 'Y' OR (reservation_to > sysdate);
delete from reservation where NVL(canceled,'N') = 'Y' OR (reservation_to > sysdate);

8. Utilizatori :

admin : administrator/administrator
reports : management/management
campaign : campaign/campaign