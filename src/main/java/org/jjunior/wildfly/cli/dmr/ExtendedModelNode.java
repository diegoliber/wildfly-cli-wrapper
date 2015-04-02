package org.jjunior.wildfly.cli.dmr;

import org.jboss.as.controller.client.helpers.ClientConstants;
import org.jboss.dmr.ModelNode;

public class ExtendedModelNode {
	
	private ModelNode modelNode;

	public ExtendedModelNode(ModelNode modelNode) {
		this.modelNode = modelNode;
	}
	
	public boolean isSuccessOutcome() {
		ModelNode outcome = modelNode.get(ClientConstants.OUTCOME);
		return outcome.asString().equals((ClientConstants.SUCCESS));
	}
	
}
