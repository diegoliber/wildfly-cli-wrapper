package org.jjunior.widlfly.cli.resource;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.jboss.dmr.ModelNode;
import org.jjunior.wildfly.cli.resource.ResourceUtil;
import org.junit.Before;
import org.junit.Test;

public class ResourceUtilTest {

	private static String ROOT = "src/test/resources/";
	private ModelNode result;

	@Before
	public void setUp() throws Exception {
		result = modelNodeFromJSONFile("result_1.json");
	}

	@Test
	public void shouldntBeDiffWhenAllPropertiesContainedInChangeRequestAreEqualToResult()
			throws Exception {
		ModelNode changeRequest = modelNodeFromJSONFile("small_change_request_1.json");

		assertFalse(ResourceUtil.diff(result, changeRequest));
	}

	@Test
	public void shouldBeDiffWhenChangeRequestAndResultIntersectionHaveDifferences()
			throws Exception {
		ModelNode changeRequest = modelNodeFromJSONFile("small_change_request_2.json");

		assertTrue(ResourceUtil.diff(result, changeRequest));
	}
	
	@Test
	public void shouldntBeDiffWhenChangeRequestAndResultMatch() throws Exception {
		ModelNode changeRequest = modelNodeFromJSONFile("change_request_1.json");

		assertFalse(ResourceUtil.diff(result, changeRequest));
	}
	
	@Test
	public void shouldBeDiffeWhenChangeRequestAndResultDoesntMatch() throws Exception {
		ModelNode changeRequest = modelNodeFromJSONFile("change_request_2.json");

		assertTrue(ResourceUtil.diff(result, changeRequest));
	}
	
	private ModelNode modelNodeFromJSONFile(String fileName)
			throws IOException, FileNotFoundException {
		return ModelNode.fromJSONStream(new FileInputStream(ROOT + fileName));
	}

}
