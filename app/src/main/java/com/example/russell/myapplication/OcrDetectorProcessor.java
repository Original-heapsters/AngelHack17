package com.example.russell.myapplication;
import android.util.SparseArray;

import com.example.russell.myapplication.ui.camera.GraphicOverlay;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;

/**
 * A very simple Processor which receives detected TextBlocks and adds them to the overlay
 * as OcrGraphics.
 */
public class OcrDetectorProcessor implements Detector.Processor<TextBlock> {

    private GraphicOverlay<OcrGraphic> mGraphicOverlay;
    private OcrCaptureActivity parent;

    OcrDetectorProcessor(GraphicOverlay<OcrGraphic> ocrGraphicOverlay, OcrCaptureActivity parentRef) {
        mGraphicOverlay = ocrGraphicOverlay;
        parent = parentRef;
    }

    @Override
    public void receiveDetections(Detector.Detections<TextBlock> detections) {
        mGraphicOverlay.clear();
        SparseArray<TextBlock> items = detections.getDetectedItems();
        for (int i = 0; i < items.size(); ++i) {
            TextBlock item = items.valueAt(i);
            String detectedText = item.getValue().replace(" ", "");

            if (checkFull(detectedText))
            {
                TicketInfo.barcodeFull = detectedText.substring(0,13);
                TicketInfo.setFull = true;
            }

            if (checkID(detectedText))
            {
                TicketInfo.barcodeID = detectedText;
                TicketInfo.setID = true;
            }

            if (checkID(detectedText) || checkFull(detectedText)) {

                OcrGraphic graphic = new OcrGraphic(mGraphicOverlay, item);
                mGraphicOverlay.add(graphic);
            }

            if (TicketInfo.setFull && TicketInfo.setID)
            {
                parent.onTap(1,1);

            }
        }
    }

    @Override
    public void release() {
        mGraphicOverlay.clear();
    }

    public boolean checkFull(String input)
    {
        boolean isFull = true;

        if (input.length() != 16)
        {
            isFull = false;
        }
        else
        {
            isFull = true;
        }

        try {
            Double.parseDouble(input);
        } catch(NumberFormatException e) {
            isFull = false;
        } catch(NullPointerException e) {
            isFull = false;
        }
        return isFull;

    }

    public boolean checkID(String input)
    {
        boolean isID = true;

        if (input.length() != 7)
        {
            isID = false;
        }

        try {
            Integer.parseInt(input);
        } catch(NumberFormatException e) {
            isID = false;
        } catch(NullPointerException e) {
            isID = false;
        }

        return isID;
    }
}
