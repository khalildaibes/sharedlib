def khaliltest(){
  def configVal = readYaml file: "full path of the yml file"
  echo "configVal: " + configVal
  echo configVal['datacenterc']["track3-migration-master"]["data-centers"]
}

