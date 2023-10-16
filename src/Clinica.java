import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Stream;

public class Clinica {
    private PriorityQueue<Paciente> listaPacientes;

    public Clinica(){
        listaPacientes=new PriorityQueue<Paciente>();
    }

    public void encolar(Paciente paciente){
        listaPacientes.add(paciente);
    }

    public Paciente atender() throws Exception{
        if(listaPacientes.isEmpty()){
            throw new Exception("Lista vacia");
        }
        return listaPacientes.remove();
    }

    public List<Paciente> enlistar(){
        return listaPacientes.stream().toList();
    }



}
