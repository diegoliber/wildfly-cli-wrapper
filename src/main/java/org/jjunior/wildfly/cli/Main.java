package org.jjunior.wildfly.cli;

import org.jboss.as.cli.CommandContext;
import org.jboss.as.cli.CommandContextFactory;
import org.jboss.as.controller.client.ModelControllerClient;
import org.jjunior.wildfly.cli.parameters.CliParameterHolder;
import org.jjunior.wildfly.cli.resource.Resource;

public class Main {

	public static void main(String[] args) throws Exception {
		
		CliParameterHolder cliParamHolder = CliParameterHolder.fromStringArguments(args);
		
		CommandContextFactory factory = CommandContextFactory.getInstance();
		CommandContext ctx = factory.newCommandContext();
		
		if (cliParamHolder.getTargetAddress() != null || !cliParamHolder.isDefaultTargetPort()) {
			ctx.connectController(cliParamHolder.getRemoteUrl());
		} else {
			ctx.connectController();
		}
		
		ModelControllerClient client = ctx.getModelControllerClient();
		
		String path = cliParamHolder.getPath();
		String params = cliParamHolder.getJsonContent();
		boolean verifyOnly = cliParamHolder.isVerifyOnly();
		
		Resource resource = new Resource(ctx, client);
		
		if (verifyOnly) {
			int status = resource.verifyOnly(path, params);
			System.exit(status);
		} else {
			resource.ensure(path, params);
			System.exit(0);
		}
		
	}

}
