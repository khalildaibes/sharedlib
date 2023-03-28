def khaliltest(){
def data = new URL ("https://raw.githubusercontent.com/khalildaibes/sharedlib/main/vars/datacenters.yml").getText()
data = readYaml file: "vars/datacenters.yml"
scan_path = data[scan_path]
scan_path.each { e ->
                echo "Translating ${e.getAt('data-centers')} application"
}
}

