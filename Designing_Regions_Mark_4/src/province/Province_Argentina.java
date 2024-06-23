package province;

import java.util.HashMap;
import java.util.Map;


public class Province_Argentina {
    
    private Map<String, Coordinates> locations;

    public Province_Argentina(){
        this.locations = new HashMap<>();
        generateProvinceArgentina();
    }

    public void generateProvinceArgentina(){
        locations.put("Buenos Aires", new Coordinates("Buenos Aires", -36.13095816508716, -60.58014633992997));
		locations.put("Ciudad Autónoma de Buenos Aires",new Coordinates("Ciudad Autónoma de Buenos Aires", -34.615, -58.433));
		locations.put("Catamarca", new Coordinates("Catamarca", -28.4696, -65.7852));
		locations.put("Chaco", new Coordinates("Chaco", -26.23197463265098, -60.5209781601999));
		locations.put("Chubut", new Coordinates("Chubut", -44.14150433779783, -68.98608540994736));
		locations.put("Córdoba", new Coordinates("Córdoba", -31.4201, -64.1888));
		locations.put("Corrientes", new Coordinates("Corrientes", -28.809516425223695, -57.90485796963525));
		locations.put("Entre Ríos", new Coordinates("Entre Ríos", -31.9120639805145, -59.359366885866294));
		locations.put("Formosa", new Coordinates("Formosa", -24.41423220907534, -60.503254977330435));
		locations.put("Jujuy", new Coordinates("Jujuy", -22.947512356234597, -65.86395373046963));
		locations.put("La Pampa", new Coordinates("La Pampa", -37.34679229627357, -65.7946188317573));
		locations.put("La Rioja", new Coordinates("La Rioja", -29.4131, -66.8558));
		locations.put("Mendoza", new Coordinates("Mendoza", -34.07663848447485, -68.53265286686458));
		locations.put("Misiones", new Coordinates("Misiones", -26.5003, -54.5837));
		locations.put("Neuquén", new Coordinates("Neuquén", -38.570528217154724, -69.8929500107004));
		locations.put("Río Negro", new Coordinates("Río Negro", -40.24130223291082, -67.05027777685493));
		locations.put("Salta", new Coordinates("Salta", -25.384620598579055, -65.06112324486749));
		locations.put("San Juan", new Coordinates("San Juan", -31.5375, -68.5364));
		locations.put("San Luis", new Coordinates("San Luis", -33.2994, -66.3356));
		locations.put("Santa Cruz", new Coordinates("Santa Cruz", -49.1640656040074, -70.29406341700088));
		locations.put("Santa Fe", new Coordinates("Santa Fe", -31.24348704906275, -61.312614086043425));
		locations.put("Santiago del Estero",new Coordinates("Santiago del Estero", -27.536920441310944, -63.45769808783292));
		locations.put("Tierra del Fuego", new Coordinates("Tierra del Fuego", -54.49115144122864, -67.55602943369655));
		locations.put("Tucumán", new Coordinates("Tucumán", -26.8241, -65.2226));
    }

    public Map<String, Coordinates> getLocations() {
        return locations;
    }
}
