package province;

public enum Province_Argentina {


    BUENOS_AIRES("Buenos Aires", -36.605, -58.435),
    CIUDAD_AUTONOMA_BUENOS_AIRES("Ciudad Autónoma de Buenos Aires", -34.615, -58.433),
    CATAMARCA("Catamarca", -28.4696, -65.7852),
    CHACO("Chaco", -27.4512, -58.9866),
    CHUBUT("Chubut", -43.3002, -65.1023),
    CORDOBA("Córdoba", -31.4201, -64.1888),
    CORRIENTES("Corrientes", -27.4692, -58.8302),
    ENTRE_RIOS("Entre Ríos", -31.6222, -60.7299),
    FORMOSA("Formosa", -26.1852, -58.1761),
    JUJUY("Jujuy", -24.1858, -65.2995),
    LA_PAMPA("La Pampa", -36.6167, -64.2833),
    LA_RIOJA("La Rioja", -29.4131, -66.8558),
    MENDOZA("Mendoza", -32.8908, -68.8272),
    MISIONES("Misiones", -26.5003, -54.5837),
    NEUQUEN("Neuquén", -38.9516, -68.0591),
    RIO_NEGRO("Río Negro", -40.8128, -63.0012),
    SALTA("Salta", -24.7859, -65.4117),
    SAN_JUAN("San Juan", -31.5375, -68.5364),
    SAN_LUIS("San Luis", -33.2994, -66.3356),
    SANTA_CRUZ("Santa Cruz", -50.0000, -69.0000),
    SANTA_FE("Santa Fe", -31.6333, -60.7000),
    SANTIAGO_DEL_ESTERO("Santiago del Estero", -27.7834, -64.2649),
    TIERRA_DEL_FUEGO("Tierra del Fuego", -54.8000, -68.3000),
    TUCUMAN("Tucumán", -26.8241, -65.2226);

    private final String name;
    private final double latitude;
    private final double longitude;

        Province_Argentina(String name, double latitude, double longitude) {
            this.name = name;
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public String getName() {
            return name;
        }

        public double getLatitude() {
            return latitude;
        }

        public double getLongitude() {
            return longitude;
        }

}
