package com.infsb.commons.utils;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class DataSourcesConfigurations {
	@XmlElement(name = "datasource")
	public List<DatasourceItems> items;
}
