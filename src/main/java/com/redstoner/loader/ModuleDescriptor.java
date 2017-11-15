package com.redstoner.loader;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public final class ModuleDescriptor
{
	private static final ObjectMapper yamlObjectMapper;
	private String name;
	private Version version;
	private String mainClass;
	private List<String> dependencies;
	private List<String> softDependencies;

	public ModuleDescriptor() {

	}

	public ModuleDescriptor(String name, Version version, String mainClass, List<String> dependencies, List<String> softDependencies)
	{
		this.name = name;
		this.version = version;
		this.mainClass = mainClass;
		this.dependencies = dependencies;
		this.softDependencies = softDependencies;
	}

	static {
		yamlObjectMapper = new ObjectMapper(new YAMLFactory());
		yamlObjectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.KEBAB_CASE);

		SimpleModule module = new SimpleModule();
		module.addDeserializer(Version.class, new JsonDeserializer<Version>()
		{
			@Override
			public Version deserialize(JsonParser p, DeserializationContext ctxt) throws IOException
			{
				JsonNode node = p.readValueAsTree();
				String text = node.asText();
				Version version = Version.tryParse(text);
				if (version == null) {
					throw new IOException("Invalid version: " + text);
				}
				return version;
			}
		});
	}

	public static ModuleDescriptor load(String content) throws IOException
	{
		return yamlObjectMapper.readValue(content, ModuleDescriptor.class);
	}

	public static ModuleDescriptor load(InputStream inputStream) throws IOException
	{
		return yamlObjectMapper.readValue(inputStream, ModuleDescriptor.class);
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Version getVersion()
	{
		return version;
	}

	public void setVersion(Version version)
	{
		this.version = version;
	}

	public String getMainClass()
	{
		return mainClass;
	}

	public void setMainClass(String mainClass)
	{
		this.mainClass = mainClass;
	}

	public List<String> getDependencies()
	{
		return dependencies;
	}

	public void setDependencies(List<String> dependencies)
	{
		this.dependencies = dependencies;
	}

	public List<String> getSoftDependencies()
	{
		return softDependencies;
	}

	public void setSoftDependencies(List<String> softDependencies)
	{
		this.softDependencies = softDependencies;
	}

}
