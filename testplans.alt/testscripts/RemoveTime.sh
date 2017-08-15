cp ${experiment_root}/apache-xml-security/outputs/t$1 ${experiment_root}/apache-xml-security/outputs/tmp
sed '/Time:/d' ${experiment_root}/apache-xml-security/outputs/tmp > ${experiment_root}/apache-xml-security/outputs/t$1
cp ${experiment_root}/apache-xml-security/outputs/t$1 ${experiment_root}/apache-xml-security/outputs/tmp
sed '/Finished after/d' ${experiment_root}/apache-xml-security/outputs/tmp > ${experiment_root}/apache-xml-security/outputs/t$1
rm ${experiment_root}/apache-xml-security/outputs/tmp
