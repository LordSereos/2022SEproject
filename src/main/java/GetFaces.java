import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GetFaces {

    public List<Line> FacesFromCoords(JsonReader Roof) {

        List<Line> FacesFromCoords = new ArrayList<>();


        int n = 0;
        int R = Roof.getInfo().getRoofs().size();

        for (int i = 0; i < R - 1; i++) {

            Line templine = new Line();

            if (Objects.equals(Roof.getInfo().getRoofs().get(i).getPoints().get(1).getId(), Roof.getInfo().getRoofs().get(i + 1).getPoints().get(0).getId())) {

                templine.setP1(Roof.getInfo().getRoofs().get(i).getPoints().get(0));
                templine.setP2(Roof.getInfo().getRoofs().get(i).getPoints().get(1));
                FacesFromCoords.add(templine);
                n++;

            } else if (Objects.equals(Roof.getInfo().getRoofs().get(n + 1).getPoints().get(0).getId(), Roof.getInfo().getRoofs().get(i - n).getPoints().get(1).getId())) {

                templine.setP1(Roof.getInfo().getRoofs().get(i).getPoints().get(0));
                templine.setP2(Roof.getInfo().getRoofs().get(i).getPoints().get(1));
                FacesFromCoords.add(templine);
                n = 1;

            }
            if (R - 2 == i) {
                templine = new Line();

                templine.setP1(Roof.getInfo().getRoofs().get(R - 1).getPoints().get(0));
                templine.setP2(Roof.getInfo().getRoofs().get(R - 1).getPoints().get(1));
                FacesFromCoords.add(templine);

                if (!Objects.equals(Roof.getInfo().getRoofs().get(R - 1).getPoints().get(1).getId(), Roof.getInfo().getRoofs().get(R - 1 - n).getId())) {
                    templine = new Line();

                    templine.setP1(Roof.getInfo().getRoofs().get(R - 1).getPoints().get(1));
                    templine.setP2(Roof.getInfo().getRoofs().get(R - n).getPoints().get(0));
                    FacesFromCoords.add(templine);


                }

            }


        }


        return FacesFromCoords;

    }
}




