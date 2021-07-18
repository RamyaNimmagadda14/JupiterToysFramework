# JupiterToysFramework

To test Jupiter application, POM is implemeted. It is a design pattern, popularly used in test automation that creates Object Repository for web UI elements. The advantage of the model is that it reduces code duplication and improves test maintenance.

Steps:

1. Download or clone the repository: https://github.com/RamyaNimmagadda14/JupiterToysFramework.git
2. Import the downloaded code in any of the below supported IDEs:
	•	Eclipse
	•	Intellij IDEA
3. Below dependencies have been used.
	•	Selenium with Java - For Automation
	•	TestNG  - For test assertions, Data Provider
	•	Maven - Build tool
	•	Apache POI - For data driven test approach. To integrate with excel data source
	•	Extent Report - To generate reports based on execution

4. Below is the brief description about each of the Java classes:

	Location >> JupiterToysFramework/JupiterToysFramework/src/main/java/com/jupiter/pages
	we have  6 pages
		CartPage.Java - relaed to cart in the page
		ContactPage.Java - relaed to Contact in the page
		HomePage.Java - relaed to Home in the page
		ShopPage.Java - relaed to Shop in the page
		Base.Java - Placeholder for all test related functions
		Common.Java - Placeholder for all re-usable actions

5. Utilities Location : JupiterToysFramework/JupiterToysFramework/src/main/java/com/jupiter/utils
   Contains all the utilities such as excel data, Properties, Reports etc.

6. Test Case Location : JupiterToysFramework/JupiterToysFramework/src/test/java/com/jupiter/tests
	Test Cases are grouped at page level checks. 
	
7. Steps to execute the code:
	cd C:\JupiterToysFramework
    mvn clean test -DsuiteXmlFile=testNG.xml

8. Configuration Location JupiterToysFramework/JupiterToysFramework/config.properties
	To update the Jupiter URL location, Browser