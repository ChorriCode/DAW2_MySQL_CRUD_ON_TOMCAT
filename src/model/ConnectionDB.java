package model;

public class ConnectionDB {

    private String url;
    private String dbName;
    private String driver;
    private String userName;
    private String password;
    private String tabla;
    private boolean validConnection = false;
	
    
    //El constructor crea un objeto con todos los datos que necesitamos para conectar un una base de datos
    public ConnectionDB(String url, String dbName, String driver, String userName, String password) {
		super();
		this.url = url;
		this.dbName = dbName;
		this.driver = driver;
		this.userName = userName;
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTabla() {
		return tabla;
	}

	public void setTabla(String tabla) {
		this.tabla = tabla;
	}

	public boolean isValidConnection() {
		return validConnection;
	}

	public void setValidConnection(boolean validConnection) {
		this.validConnection = validConnection;
	}
    
    
	
}
