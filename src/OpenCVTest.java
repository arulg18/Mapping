import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

public class OpenCVTest {
    CascadeClassifier face_cascade;
    Mat matrix = new Mat();
    MatOfRect rects;
    public void main(String[] args)
    {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        VideoCapture capture = new VideoCapture(0);
        face_cascade.load("eyeclassifier.xml");

        while(true) {
            capture.read(matrix);
            face_cascade.detectMultiScale(matrix,rects);

            if(rects.empty())
            {
                System.out.println("no eye :/");
            }
            else {
                System.out.println("i see eyes!");
            }
            try{
            wait(500);}
            catch(Exception e){}
        }

    }
}
