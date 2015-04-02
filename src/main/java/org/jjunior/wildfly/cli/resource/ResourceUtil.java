package org.jjunior.wildfly.cli.resource;

import java.util.List;

import org.jboss.dmr.ModelNode;
import org.jboss.dmr.Property;

public class ResourceUtil {
	
	public static boolean diff(ModelNode result, ModelNode changeRequest) {
		List<Property> results = result.asPropertyList();
		List<Property> changes = changeRequest.asPropertyList();
		
		for (Property changedProperty : changes) {
			
			String changedPropertyName = changedProperty.getName();
			String oldPropertyValue = propertyValueByName(results, changedPropertyName);
			
			if (!changedProperty.getValue().asString().equals(oldPropertyValue)) {
				return true;
			}
		}
		
		return false;
	}
	
	private static String propertyValueByName(List<Property> properties, String name) {
		String value = null;
		
		for (Property property : properties) {
			if (property.getName().equals(name)) {
				value = property.getValue().asString();
			}
		}
		
		return value;
	}

	public static String assembleParameters(ModelNode changeRequest) {
		StringBuilder params = new StringBuilder();
		
		for (Property property : changeRequest.asPropertyList()) {
			params.append(String.format("%s=%s,", property.getName(), property.getValue().asString()));
		}
		
		params.deleteCharAt(params.length() - 1);
		
		return params.toString();
	}

}
