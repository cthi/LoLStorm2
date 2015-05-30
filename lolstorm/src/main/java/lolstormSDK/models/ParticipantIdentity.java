package lolstormSDK.models;


import org.parceler.Parcel;

@Parcel
public class ParticipantIdentity {
    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

     int participantId;
     Player player;
}
