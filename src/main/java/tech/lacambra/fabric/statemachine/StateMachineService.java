package tech.lacambra.fabric.statemachine;

import com.fasterxml.jackson.annotation.JacksonInject;
import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;

import static guru.nidi.graphviz.model.Factory.mutGraph;

@Service
public class StateMachineService {

//    @Autowired
//    StateMachine<ExStateMachine.States, ExStateMachine.Events> stateMachine;
//
//    @Bean
//    public Graphviz graphviz() throws Exception {
//        MutableGraph graph = mutGraph("Order State Machine").setDirected(true);
//
//        Map<String, Set<GraphVizLink>> graphMap = new HashMap<>();
//        stateMachine.getStates().forEach(
//                s -> graphMap.put(s.getId().name(), new HashSet<>())
//        );
//
//        stateMachine.getTransitions().forEach(t -> {
//            graphMap.get(t.getSource().getId().name()).add(
//                    new GraphVizLink(t.getTarget().getId().name(), t.getTrigger().getEvent().name())
//            );
//
//            possibleTransitions.put(t.getSource().getId().name(), t.getTrigger().getEvent().name());
//        });
//
//        graphMap.forEach((key, targetSet) -> {
//            Node node = node(key).with(Shape.RECTANGLE);
//            targetSet.forEach(l -> graph.add(
//                    node.link(to(node(l.getTarget())).with(Label.of(l.getLabel().toLowerCase())))
//            ));
//        });
//
//        return Graphviz.fromGraph(graph);
//    }

}