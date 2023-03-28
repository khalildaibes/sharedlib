mydata = readYaml file: "test.yml"

// modify
data = readYaml file: "datacenters.yml"
scan_path = data[scan_path]
scan_path.each { e ->
                echo "Translating ${e.getAt('data-centers')} application"
}

