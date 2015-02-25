package Comparator;

public class ObjetoVO {
	
	private String nombre;
	private String apellidos;
	
	public ObjetoVO(String nombre, String apellidos){
		this.nombre = nombre;
		this.apellidos = apellidos;
	}
	public ObjetoVO(String nombre){
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	@Override
    public boolean equals(Object obj) {
		if (!(obj instanceof ObjetoVO))
			return false;
		ObjetoVO objetoVO = (ObjetoVO) obj;
		return (this.getNombre().equalsIgnoreCase(objetoVO.getNombre()));
    }

	@Override
	public int hashCode() {
		return super.hashCode();
	}

}
