package com.reno.lolchesscombination.model

import com.reno.lolchesscombination.model.CombinationRule.Companion.DEMON
import com.reno.lolchesscombination.model.CombinationRule.Companion.ELEMANTALIST
import com.reno.lolchesscombination.model.CombinationRule.Companion.GLACIAL
import com.reno.lolchesscombination.model.CombinationRule.Companion.RANGER

data class CombinationRule(
    val name: String,
    val combinationCount: IntArray,
    val effectDescription: Array<String>
) {
    companion object {
        val DEMON = CombinationRule(
            "악마",
            intArrayOf(2, 4, 6),
            arrayOf(
                "히트시 30% 확률로 타겟의 현재 마나번, 마나번 만큼 트루 데미지",
                "히트시 50% 확률로 타겟의 현재 마나번, 마나번 만큼 트루 데미지",
                "히트시 70% 확률로 타겟의 현재 마나번, 마나번 만큼 트루 데미지"
            )
        )

        val GLACIAL = CombinationRule(
            "빙하",
            intArrayOf(2, 4, 6),
            arrayOf(
                "히트시 25% 확률로 2초간 스턴",
                "히트시 35% 확률로 2초간 스턴",
                "히트시 45% 확률로 2초간 스턴"
            )
        )

        val RANGER = CombinationRule(
            "레인저",
            intArrayOf(2, 4),
            arrayOf(
                "매 3초마다 25% 확률로 3초간 공속 2배",
                "매 3초마다 65% 확률로 3초간 공속 2배"
            )
        )

        val ELEMANTALIST = CombinationRule(
            "원소술사",
            intArrayOf(3),
            arrayOf("매 전투마다 정령 소환")
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CombinationRule

        if (name != other.name) return false
        if (!combinationCount.contentEquals(other.combinationCount)) return false
        if (!effectDescription.contentEquals(other.effectDescription)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + combinationCount.contentHashCode()
        result = 31 * result + effectDescription.contentHashCode()
        return result
    }
}

data class Champion(
    val id: Int,
    val name: String,
    val price: Int,
    val imgUrl: String,
    val combinations: Array<CombinationRule>
) {
    companion object {
        val mockList: ArrayList<Champion> = arrayListOf(
            Champion(1, "이블린", 3, "imageUrl", arrayOf(DEMON)),
            Champion(2, "아트록스", 2, "imageUrl", arrayOf(DEMON)),
            Champion(3, "브랜드", 4, "imageUrl", arrayOf(DEMON, ELEMANTALIST)),
            Champion(4, "바루스", 2, "imageUrl", arrayOf(DEMON, RANGER)),
            Champion(5, "엘리스", 2, "imageUrl", arrayOf(DEMON)),
            Champion(6, "모르가나", 3, "imageUrl", arrayOf(DEMON)),
            Champion(7, "볼리베어", 2, "imageUrl", arrayOf(GLACIAL)),
            Champion(8, "리산드라", 1, "imageUrl", arrayOf(GLACIAL, ELEMANTALIST)),
            Champion(9, "브라움", 2, "imageUrl", arrayOf(GLACIAL)),
            Champion(10, "세주아니", 4, "imageUrl", arrayOf(GLACIAL)),
            Champion(11, "애쉬", 3, "imageUrl", arrayOf(GLACIAL, RANGER)),
            Champion(12, "애니비아", 5, "imageUrl", arrayOf(GLACIAL, ELEMANTALIST)),
            Champion(13, "베인", 1, "imageUrl", arrayOf(RANGER)),
            Champion(14, "카서스", 5, "imageUrl", arrayOf(RANGER)),
            Champion(15, "브랜드", 4, "imageUrl", arrayOf(ELEMANTALIST)),
            Champion(16, "케넨", 3, "imageUrl", arrayOf(ELEMANTALIST))
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Champion

        if (id != other.id) return false
        if (name != other.name) return false
        if (price != other.price) return false
        if (imgUrl != other.imgUrl) return false
        if (!combinations.contentEquals(other.combinations)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + price
        result = 31 * result + imgUrl.hashCode()
        result = 31 * result + combinations.contentHashCode()
        return result
    }
    /**
    Champion(1, "이블린", 3, "imageUrl"),
    Champion(2, "아트록스", 2, "imageUrl"),
    Champion(3, "브랜드", 4, "imageUrl"),
    Champion(4, "바루스", 2, "imageUrl"),
    Champion(5, "엘리스", 2, "imageUrl"),
    Champion(6, "모르가나", 3, "imageUrl")
     */

    /*
    Champion(7, "볼리베어", 2, "imageUrl"),
    Champion(8, "리산드라", 1, "imageUrl"),
    Champion(9, "브라움", 2, "imageUrl"),
    Champion(10, "세주아니", 4, "imageUrl"),
    Champion(11, "애쉬", 3, "imageUrl"),
    Champion(12, "애니비아", 5, "imageUrl")
    * */

    /**
     *  Champion(4, "바루스", 2, "imageUrl"),
     *  Champion(11, "애쉬", 3, "imageUrl"),
     *  Champion(13, "베인", 1, "imageUrl"),
     *  Champion(14, "카서스", 5, "imageUrl"),
     * */

    /**
     *  Champion(15, "브랜드", 4, "imageUrl"),
     *  Champion(8, "리산드라", 1, "imageUrl"),
     *  Champion(12, "애니비아", 5, "imageUrl"),
     *  Champion(16, "케넨", 3, "imageUrl"),
     * */


}
