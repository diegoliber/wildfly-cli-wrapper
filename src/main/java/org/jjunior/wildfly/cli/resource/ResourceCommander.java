package org.jjunior.wildfly.cli.resource;

import java.io.IOException;

import org.jboss.as.cli.CommandContext;
import org.jboss.as.cli.CommandFormatException;
import org.jboss.as.controller.client.ModelControllerClient;
import org.jboss.as.controller.client.helpers.ClientConstants;
import org.jboss.dmr.ModelNode;

public class ResourceCommander {
	
	private String path;
	private CommandContext ctx;
	private ModelControllerClient client;

	public ResourceCommander(String path, CommandContext ctx, ModelControllerClient client) {
		this.path = path;
		this.ctx = ctx;
		this.client = client;
	}
	
	public void update(ModelNode changeRequest) throws CommandFormatException, IOException {
		remove();
		insert(changeRequest);
	}

	public void remove() throws CommandFormatException, IOException {
		String cmd = String.format("%s:%s", path, ClientConstants.REMOVE_OPERATION);
		
		client.execute(ctx.buildRequest(cmd));
	}

	public void insert(ModelNode changeRequest) throws CommandFormatException, IOException {
		String parameters = ResourceUtil.assembleParameters(changeRequest);
		String cmd = String.format("%s:%s(%s)", path, ClientConstants.ADD, parameters);
		
		client.execute(ctx.buildRequest(cmd));
	}

}
