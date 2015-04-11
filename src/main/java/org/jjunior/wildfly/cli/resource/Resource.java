package org.jjunior.wildfly.cli.resource;

import org.jboss.as.cli.CommandContext;
import org.jboss.as.cli.CommandLineException;
import org.jboss.as.controller.client.ModelControllerClient;
import org.jboss.as.controller.client.helpers.ClientConstants;
import org.jboss.dmr.ModelNode;
import org.jjunior.wildfly.cli.dmr.ExtendedModelNode;

public class Resource {
	
	private CommandContext ctx;
	private ModelControllerClient client;
	
	public Resource(CommandContext ctx, ModelControllerClient client) throws CommandLineException {
		this.ctx = ctx;
		this.client = client;
	}

	public void ensure(String path, String params) throws Exception {
		ModelNode changeRequest = ModelNode.fromJSONString(params);
		
		ModelNode readRequest = ctx.buildRequest(String.format("%s:%s", path, ClientConstants.READ_RESOURCE_OPERATION));
		ModelNode response = client.execute(readRequest);
		
		ExtendedModelNode extendedResponse = new ExtendedModelNode(response);
		
		ResourceCommander commander = new ResourceCommander(path, ctx, client);
		
		if (extendedResponse.isSuccessOutcome()) {
			ModelNode result = response.get(ClientConstants.RESULT);

			if (ResourceUtil.diff(result, changeRequest)) {
				commander.update(changeRequest);
			}
			
		} else {
			commander.insert(changeRequest);
		}
		
	}
	
	public int verifyOnly(String path, String params) throws Exception {
		int exitCode = 0;
		
		ModelNode changeRequest = ModelNode.fromJSONString(params);
		
		ModelNode readRequest = ctx.buildRequest(String.format("%s:%s", path, ClientConstants.READ_RESOURCE_OPERATION));
		ModelNode response = client.execute(readRequest);
		
		ExtendedModelNode extendedResponse = new ExtendedModelNode(response);
		
		if (extendedResponse.isSuccessOutcome()) {
			if (ResourceUtil.diff(extendedResponse.getResult(), changeRequest)) {
				exitCode = 1;
			}
			
		} else {
			exitCode = 1;
		}
		
		return exitCode;
	}

}
