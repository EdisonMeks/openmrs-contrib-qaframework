package org.openmrs.contrib.qaframework.automation;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.contrib.qaframework.page.AllergyPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AllergiesSteps extends Steps {
	private AllergyPage allergyPage;

	@Before(RunTest.HOOK.SELENIUM_DASHBOARD)
	public void visitDashboard() {
		initiatePatientDashboard();
	}

	@After(RunTest.HOOK.SELENIUM_DASHBOARD)
	public void destroy() {
		quit();
	}

	@Given("a user clicks on Allergies link from Patient dashboard")
	public void loadAllergiesPage() {
		allergyPage = (AllergyPage) dashboardPage.clickOnAllergiesWidgetLink()
				.waitForPage();
	}

	@Then("the system loads Allergies page")
	public void systemLoadsAllergiesPage() {
		assertEquals(getElement(patientHeaderId).getText(),
				getElement(patientHeaderId).getText());
		assertTrue(textExists("Allergies"));
	}

	@And("a user clicks No Known Allergy button")
	public void addNoKnownAllergy() {
		allergyPage.addNoKnownAllergy();
	}

	@Then("the system add no known allergies into the allergies table")
	public void systemAddsNoKnownAllergies() {
		assertTrue(textExists("No known allergies"));
	}

	@And("a user clicks Remove No Known Allergy icon")
	public void removeNoKnownAllergy() {
		allergyPage.removeNoKnownAllergy();
	}

	@Then("the system displays unknown in the allergies table")
	public void systemRemovesNoKnownAllergies() {
		assertTrue(textExists("Unknown"));
	}

}
