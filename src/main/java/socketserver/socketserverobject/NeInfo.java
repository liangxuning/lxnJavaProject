package socketserver.socketserverobject;

import java.io.Serializable;

public class NeInfo implements Serializable {
	String ne_id;
    int vendor_id;
    int ne_type_id;
    int ne_subtype_id;
    String ne_name;
    String node_category;
    String manage_node_id;
    int source_id;
    int source_ne_id;
    String source_ne_name;
    String ne_version;
    String region_name;
    String ne_ip;
    String acc_name;
    String acc_pswd;
    int acc_source;
    
	public String getNe_id() {
		return ne_id;
	}
	public void setNe_id(String neId) {
		ne_id = neId;
	}
	public int getVendor_id() {
		return vendor_id;
	}
	public void setVendor_id(int vendorId) {
		vendor_id = vendorId;
	}
	public int getNe_type_id() {
		return ne_type_id;
	}
	public void setNe_type_id(int neTypeId) {
		ne_type_id = neTypeId;
	}
	public int getNe_subtype_id() {
		return ne_subtype_id;
	}
	public void setNe_subtype_id(int neSubtypeId) {
		ne_subtype_id = neSubtypeId;
	}
	public String getNe_name() {
		return ne_name;
	}
	public void setNe_name(String neName) {
		ne_name = neName;
	}
	public String getNode_category() {
		return node_category;
	}
	public void setNode_category(String nodeCategory) {
		node_category = nodeCategory;
	}
	public String getManage_node_id() {
		return manage_node_id;
	}
	public void setManage_node_id(String manageNodeId) {
		manage_node_id = manageNodeId;
	}
	public int getSource_id() {
		return source_id;
	}
	public void setSource_id(int sourceId) {
		source_id = sourceId;
	}
	public int getSource_ne_id() {
		return source_ne_id;
	}
	public void setSource_ne_id(int sourceNeId) {
		source_ne_id = sourceNeId;
	}
	public String getSource_ne_name() {
		return source_ne_name;
	}
	public void setSource_ne_name(String sourceNeName) {
		source_ne_name = sourceNeName;
	}
	public String getNe_version() {
		return ne_version;
	}
	public void setNe_version(String neVersion) {
		ne_version = neVersion;
	}
	public String getRegion_name() {
		return region_name;
	}
	public void setRegion_name(String regionName) {
		region_name = regionName;
	}
	public String getNe_ip() {
		return ne_ip;
	}
	public void setNe_ip(String neIp) {
		ne_ip = neIp;
	}
	public String getAcc_name() {
		return acc_name;
	}
	public void setAcc_name(String accName) {
		acc_name = accName;
	}
	public String getAcc_pswd() {
		return acc_pswd;
	}
	public void setAcc_pswd(String accPswd) {
		acc_pswd = accPswd;
	}
	public int getAcc_source() {
		return acc_source;
	}
	public void setAcc_source(int accSource) {
		acc_source = accSource;
	}
}
