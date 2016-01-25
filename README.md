# Acme QA

Browser Test Automation Code (Selenium)


test location: com.acme.qa.site.bluntusa.test.functional.cart.AddSingleItem

structure:
	src/test/java
		com/acme/qa
				common
				site/bluntusa
					dao
					model
						data
						ui	
					test
						functional
						workflow


				
run: mvn -Dtest=AddSingleItem clean test
