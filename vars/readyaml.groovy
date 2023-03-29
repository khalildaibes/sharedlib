def khaliltest(){
  def configVal = readYaml file: "datacenters.yml"
  echo "configVal: " + configVal
  echo configVal['datacenterc']["compliance"]["data-centers"][0]
}

