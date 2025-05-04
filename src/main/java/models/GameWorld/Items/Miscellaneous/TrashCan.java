package models.GameWorld.Items.Miscellaneous;

public enum TrashCan {
    PRIMARY(0.00),
    COPPER(0.15),
    SILVER(0.30),
    GOLD(0.45),
    IRIDIUM(0.60);

    private final double refundPercentage;

    TrashCan(double refundPercentage) {
        this.refundPercentage = refundPercentage;
    }

    public double getRefundPercentage() {
        return refundPercentage;
    }
}
