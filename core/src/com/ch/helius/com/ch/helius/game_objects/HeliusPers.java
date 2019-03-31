package com.ch.helius.com.ch.helius.game_objects;

public class HeliusPers extends GamePers {

    private boolean up = true;

    public HeliusPers(float x, float y, int width, int height) {
        super( x, y, width, height );


    }


    public void isUp (boolean up) {

        this.up = up;
    }

    public void onShift(int shiftTo){

        if (up){
            switch (shiftTo){

                case -2:{   //right

                    break;
                }

                case 2:{   //left

                    break;
                }

                case -1:{   //down

                    break;
                }

                case 1:{   //up

                    break;
                }
            }
        }

    }




}
