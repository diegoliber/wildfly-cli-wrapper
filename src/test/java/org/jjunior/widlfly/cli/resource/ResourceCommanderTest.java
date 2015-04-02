package org.jjunior.widlfly.cli.resource;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.jboss.as.cli.CommandContext;
import org.jboss.as.controller.client.ModelControllerClient;
import org.jboss.as.controller.client.helpers.ClientConstants;
import org.jboss.dmr.ModelNode;
import org.jjunior.wildfly.cli.resource.ResourceCommander;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ResourceCommanderTest {

	private static final String PATH = null;
	@Mock
	private CommandContext ctxMock;
	@Mock
	private ModelControllerClient clientMock;
	
	private ResourceCommander resourceCommander;
	
	@Before
	public void setUp() {
		resourceCommander = new ResourceCommander(PATH, ctxMock, clientMock);
	}

	@Test
	public void remove() throws Exception {
		String cmd = String.format("%s:%s", PATH, ClientConstants.REMOVE_OPERATION);
		ModelNode dummyModelNode = new ModelNode();
		Mockito.when(ctxMock.buildRequest(cmd)).thenReturn(dummyModelNode);
	
		resourceCommander.remove();
		
		verify(ctxMock, times(1)).buildRequest(cmd);
		verify(clientMock, times(1)).execute(dummyModelNode);
	}

}
