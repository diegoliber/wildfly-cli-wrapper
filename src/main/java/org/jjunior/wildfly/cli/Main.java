package org.jjunior.wildfly.cli;

import org.jboss.as.cli.CommandContext;
import org.jboss.as.cli.CommandContextFactory;
import org.jboss.as.controller.client.ModelControllerClient;
import org.jjunior.wildfly.cli.resource.Resource;

public class Main {

	public static void main(String[] args) throws Exception {
		CommandContext ctx = CommandContextFactory.getInstance()
				.newCommandContext();
		
		ctx.connectController();
		
		ModelControllerClient client = ctx.getModelControllerClient();
		
		String path = args[0];
		String params = args[1];
		boolean verifyOnly = args.length > 2 && args[2].equals("--verify-only");
		
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
