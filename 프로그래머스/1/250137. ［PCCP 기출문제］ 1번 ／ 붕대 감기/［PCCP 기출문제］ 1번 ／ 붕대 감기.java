class Solution {
    /**
    * t초 동안 붕대 -> 1초마자 x만큼 체력 회복 / t 초 연속으로 하면 +y
    * 최대 체력 존재
    * 공격을 당하면 기술 취소 & 체력 회복 X -> 붕대 감시 다시 사용 -> 연속 사용 시간 = 0
    * 공격을 받으면 정해진 피해량만큼 체력 줄어즘
    * 체력이 0이하? 죽음 => 끝까지 생존 가능?
    */
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = health;
        int maxTimes = attacks[attacks.length-1][0];
        int repeatTimes = 0;
        int j = 0;
        for(int i=1;i<=maxTimes;i++){
            
            if(attacks[j][0] == i){
                repeatTimes = 0;
                answer -= attacks[j++][1];
                if(answer <= 0)
                    return -1;
                continue;
            }
            if(++repeatTimes == bandage[0]){
                repeatTimes = 0;
                answer = Math.min(health, answer+bandage[2]);
            }
            answer = Math.min(health, answer+bandage[1]);

        }
        return answer;
    }
}