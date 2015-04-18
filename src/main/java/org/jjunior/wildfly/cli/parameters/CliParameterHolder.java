package org.jjunior.wildfly.cli.parameters;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class CliParameterHolder {
	
	public static final Integer DEFAULT_TARGET_PORT = 9999;
	
	@Parameter(names = {"-p", "--path"})
	private String path;
	
	@Parameter(names = {"-c", "--content"})
	private String jsonContent;
	
	@Parameter(names = {"-a", "--target-address"})
	private String targetAddress = "localhost";
	
	@Parameter(names = {"-t", "--target-port"})
	private Integer targetPort = DEFAULT_TARGET_PORT;
	
	@Parameter(names = {"-o", "--verify-only"})
	private boolean verifyOnly;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getJsonContent() {
		return jsonContent;
	}

	public void setJsonContent(String jsonContent) {
		this.jsonContent = jsonContent;
	}

	public String getTargetAddress() {
		return targetAddress;
	}

	public void setTargetAddress(String bindAddress) {
		this.targetAddress = bindAddress;
	}

	public boolean isVerifyOnly() {
		return verifyOnly;
	}

	public void setVerifyOnly(boolean verifyOnly) {
		this.verifyOnly = verifyOnly;
	}

	public Integer getTargetPort() {
		return targetPort;
	}

	public void setTargetPort(Integer targetPort) {
		this.targetPort = targetPort;
	}
	
	public boolean isDefaultTargetPort() {
		return this.targetPort.equals(DEFAULT_TARGET_PORT);
	}

	public String getRemoteUrl() {
		//"http-remoting://localhost:9990"
		StringBuilder builder = new StringBuilder();
		builder.append("http-remoting://")
		 .append(this.getTargetAddress())
		 .append(":")
		 .append(this.getTargetPort());
		
	    return builder.toString();
    }

	@Override
    public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result
	            + ((jsonContent == null) ? 0 : jsonContent.hashCode());
	    result = prime * result + ((path == null) ? 0 : path.hashCode());
	    result = prime * result
	            + ((targetAddress == null) ? 0 : targetAddress.hashCode());
	    result = prime * result
	            + ((targetPort == null) ? 0 : targetPort.hashCode());
	    result = prime * result + (verifyOnly ? 1231 : 1237);
	    return result;
    }

	@Override
    public boolean equals(Object obj) {
	    if (this == obj)
		    return true;
	    if (obj == null)
		    return false;
	    if (getClass() != obj.getClass())
		    return false;
	    CliParameterHolder other = (CliParameterHolder) obj;
	    if (jsonContent == null) {
		    if (other.jsonContent != null)
			    return false;
	    } else if (!jsonContent.equals(other.jsonContent))
		    return false;
	    if (path == null) {
		    if (other.path != null)
			    return false;
	    } else if (!path.equals(other.path))
		    return false;
	    if (targetAddress == null) {
		    if (other.targetAddress != null)
			    return false;
	    } else if (!targetAddress.equals(other.targetAddress))
		    return false;
	    if (targetPort == null) {
		    if (other.targetPort != null)
			    return false;
	    } else if (!targetPort.equals(other.targetPort))
		    return false;
	    if (verifyOnly != other.verifyOnly)
		    return false;
	    return true;
    }
	
	public static CliParameterHolder fromStringArguments(String[] args) {
		CliParameterHolder paramHolder = new CliParameterHolder();
		new JCommander(paramHolder, args);
		return paramHolder;
	}
	
}
