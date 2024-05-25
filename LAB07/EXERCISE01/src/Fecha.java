public class Fecha {
    private int dia;
    private int mes;
    private int año;

    public Fecha() {
        this(1, 1, 1900);
    }

    public Fecha(int año) {
        this(1, 1, año);
    }

    public Fecha(int mes, int año) {
        this(1, mes, año);
    }

    public Fecha(int dia, int mes, int año) {
        this.año = año;
        this.mes = mes;
        this.dia = dia;
        validarFecha();
    }

    public Fecha(Fecha fecha) {
        this.dia = fecha.dia;
        this.mes = fecha.mes;
        this.año = fecha.año;
    }

    public Fecha(int diasTranscurridos, boolean desdeInicio) {
        if (desdeInicio) {
            calcularFechaDesdeInicio(diasTranscurridos);
        } else {
            calcularFechaDesde2000(diasTranscurridos);
        }
    }
    public void setDia(int dia) {
        this.dia = dia;
        validarFecha();
    }
    public void setMes(int mes) {
        this.mes = mes;
        validarFecha();
    }
    public void setAnio(int anio) {
        this.año = anio;
        validarFecha();
    }
    public int getDia() {
        return dia;
    }
    public int getMes() {
        return mes;
    }
    public int getAnio() {
        return año;
    }
    public String toString() {
        return dia + "/" + mes + "/" + año;
    }
    private boolean esBisiesto(int año) {
        return (año % 4 == 0 && año % 100 != 0) || (año % 400 == 0);
    }

    private void validarFecha() {
        if (mes < 1 || mes > 12) {
            mes = 1;
        }
        if (dia < 1) {
            dia = 1;
        } else if (mes == 2) {
            if (esBisiesto(año)) {
                if (dia > 29) {
                    dia = 29;
                }
            } else {
                if (dia > 28) {
                    dia = 28;
                }
            }
        } else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
            if (dia > 30) {
                dia = 30;
            }
        } else {
            if (dia > 31) {
                dia = 31;
            }
        }
    }

    private void calcularFechaDesdeInicio(int diasTranscurridos) {
        int año = 2000;
        int mes = 1;
        int dia = 1;
        while (diasTranscurridos > 0) {
            int diasEnMes = 31;
            if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
                diasEnMes = 30;
            } else if (mes == 2) {
                diasEnMes = esBisiesto(año) ? 29 : 28;
            }
            if (diasTranscurridos >= diasEnMes) {
                diasTranscurridos -= diasEnMes;
                mes++;
                if (mes > 12) {
                    mes = 1;
                    año++;
                }
            } else {
                dia += diasTranscurridos;
                diasTranscurridos = 0;
            }
        }
        this.año = año;
        this.mes = mes;
        this.dia = dia;
    }
    private void calcularFechaDesde2000(int diasTranscurridos) {
        int año = 2000;
        int mes = 1;
        int dia = 1;
        while (diasTranscurridos > 0) {
            int diasEnMes = 31;
            if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
                diasEnMes = 30;
            } else if (mes == 2) {
                diasEnMes = esBisiesto(año) ? 29 : 28;
            }
            if (diasTranscurridos >= diasEnMes) {
                diasTranscurridos -= diasEnMes;
                mes++;
                if (mes > 12) {
                    mes = 1;
                    año++;
                }
            } else {
                dia += diasTranscurridos;
                diasTranscurridos = 0;
            }
        }
        this.año = año;
        this.mes = mes;
        this.dia = dia;
    }
}

