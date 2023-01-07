package visualizer.view;

import visualizer.model.data.Edge;
import visualizer.model.data.Vertex;
import visualizer.utils.Utils;

public class Template {

    public static class TemplateOne {
        public enum TemplateForVertex {
            A(new JVertex(Utils.getNextId(), "A", 175, 359)),
            B(new JVertex(Utils.getNextId(), "B", 224, 176)),
            C(new JVertex(Utils.getNextId(), "C", 365, 363)),
            D(new JVertex(Utils.getNextId(), "D", 408, 67)),
            E(new JVertex(Utils.getNextId(), "E", 531, 235)),
            F(new JVertex(Utils.getNextId(), "F", 590, 64));

            private final JVertex jVertex;
            private final Vertex vertex;

            TemplateForVertex (JVertex jVertex) {
                this.jVertex = jVertex;
                this.vertex = new Vertex(jVertex);
            }

            public Vertex getVertex() {
                return vertex;
            }

            public JVertex getJVertex() {
                return jVertex;
            }
        }

        public enum TemplateForEdge {
            AB(TemplateForVertex.A.vertex, TemplateForVertex.B.vertex, 1),
            BD(TemplateForVertex.B.vertex, TemplateForVertex.D.vertex, 2),
            DF(TemplateForVertex.D.vertex, TemplateForVertex.F.vertex, 3),
            FE(TemplateForVertex.F.vertex, TemplateForVertex.E.vertex, 4),
            EC(TemplateForVertex.E.vertex, TemplateForVertex.C.vertex, 5),
            CA(TemplateForVertex.C.vertex, TemplateForVertex.A.vertex, 6),
            BE(TemplateForVertex.B.vertex, TemplateForVertex.E.vertex, 7),
            DC(TemplateForVertex.D.vertex, TemplateForVertex.C.vertex, 8),
            BC(TemplateForVertex.B.vertex, TemplateForVertex.C.vertex, 9),
            DE(TemplateForVertex.D.vertex, TemplateForVertex.E.vertex, 10);

            private final JEdge jEdge;
            private final JEdge jEdgeReversed;
            private final Edge edge;

            TemplateForEdge(Vertex start, Vertex end, int weight) {
                this.jEdge = new JEdge(Utils.getNextId(), start.getPoint(), end.getPoint(), start.getValue(), end.getValue(), weight);
                this.jEdgeReversed = new JEdge(jEdge.getId(), end.getPoint(), start.getPoint(), end.getValue(), start.getValue(), weight);
                this.edge = new Edge(start, end, weight, jEdge, jEdgeReversed);
                start.addEdge(edge);
                end.addEdge(edge);
            }

            public JEdge getJEdge() {
                return jEdge;
            }

            public JEdge getJEdgeReversed() {
                return jEdgeReversed;
            }

            public Edge getEdge() {
                return edge;
            }
        }
    }

    public static class TemplateTwo {
        public enum TemplateForVertex {
            ONE(new JVertex(Utils.getNextId(), "1", 361, 54)),
            TWO(new JVertex(Utils.getNextId(), "2", 152, 164)),
            THREE(new JVertex(Utils.getNextId(), "3", 599, 155)),
            FOUR(new JVertex(Utils.getNextId(), "4", 47, 394)),
            FIVE(new JVertex(Utils.getNextId(), "5", 291, 392)),
            SIX(new JVertex(Utils.getNextId(), "6", 419, 390)),
            SEVEN(new JVertex(Utils.getNextId(), "7", 744, 392));

            private final JVertex jVertex;
            private final Vertex vertex;

            TemplateForVertex (JVertex jVertex) {
                this.jVertex = jVertex;
                this.vertex = new Vertex(jVertex);
            }

            public Vertex getVertex() {
                return vertex;
            }

            public JVertex getJVertex() {
                return jVertex;
            }
        }

        public enum TemplateForEdge {
            ONE_TWO(TemplateForVertex.ONE.vertex, TemplateForVertex.TWO.vertex, 1),
            ONE_THREE(TemplateForVertex.ONE.vertex, TemplateForVertex.THREE.vertex, 3),
            TWO_FOUR(TemplateForVertex.TWO.vertex, TemplateForVertex.FOUR.vertex, 5),
            FIVE_TWO(TemplateForVertex.FIVE.vertex, TemplateForVertex.TWO.vertex, 7),
            SIX_THREE(TemplateForVertex.SIX.vertex, TemplateForVertex.THREE.vertex, 9),
            SEVEN_THREE(TemplateForVertex.SEVEN.vertex, TemplateForVertex.THREE.vertex, 11);

            private final JEdge jEdge;
            private final JEdge jEdgeReversed;
            private final Edge edge;

            TemplateForEdge(Vertex start, Vertex end, int weight) {
                this.jEdge = new JEdge(Utils.getNextId(), start.getPoint(), end.getPoint(), start.getValue(), end.getValue(), weight);
                this.jEdgeReversed = new JEdge(jEdge.getId(), end.getPoint(), start.getPoint(), end.getValue(), start.getValue(), weight);
                this.edge = new Edge(start, end, weight, jEdge, jEdgeReversed);
                start.addEdge(edge);
                end.addEdge(edge);
            }

            public JEdge getJEdge() {
                return jEdge;
            }

            public JEdge getJEdgeReversed() {
                return jEdgeReversed;
            }

            public Edge getEdge() {
                return edge;
            }
        }
    }

}
