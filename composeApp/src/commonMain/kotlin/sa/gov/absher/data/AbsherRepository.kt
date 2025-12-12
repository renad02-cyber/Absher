package sa.gov.absher.data

interface AbsherRepository {
    suspend fun login(id: String, password: String, type: UserType): User
    suspend fun getDashboardData(type: UserType): DashboardData
}

class MockAbsherRepository : AbsherRepository {
    override suspend fun login(id: String, password: String, type: UserType): User {
        kotlinx.coroutines.delay(1000)

        val name = when (type) {
            UserType.INDIVIDUAL -> "عبدالرحمن محمد الشهري"
            UserType.BUSINESS -> "شركة الآفاق التقنية للمقاولات"
            UserType.GOVERNMENT -> "الإدارة العامة للمرور - الرياض"
        }
        
        return User(
            id = id.ifBlank { "1010101010" },
            name = name,
            username = id.ifBlank { "user_123" },
            type = type
        )
    }
    
    override suspend fun getDashboardData(type: UserType): DashboardData {
        kotlinx.coroutines.delay(500)
        
        return when (type) {
            UserType.INDIVIDUAL -> getIndividualData()
            UserType.BUSINESS -> getBusinessData()
            UserType.GOVERNMENT -> getGovernmentData()
        }
    }

    private fun getIndividualData(): DashboardData {
        val services = listOf(
            Service("خدمات الجوازات", ServiceIcon.PASSPORT),
            Service("الأحوال المدنية", ServiceIcon.CARD),
            Service("خدمات المرور", ServiceIcon.TRAFFIC),
            Service("المواعيد", ServiceIcon.CALENDAR),
            Service("خدمات التابعين", ServiceIcon.USERS),
            Service("التأشيرات", ServiceIcon.VISA),
            Service("نقل الكفالة", ServiceIcon.TRANSFER),
            Service("الوثائق الرقمية", ServiceIcon.DOCUMENT)
        )
        
        val sections = listOf(
            Section(
                title = "المعلومات الشخصية",
                items = listOf(
                    InfoItem("الهوية الوطنية", "تنتهي في: 15/03/2028", "سارية"),
                    InfoItem("جواز السفر", "ينتهي في: 22/08/2029", "ساري")
                )
            ),
            Section(
                title = "المركبات",
                items = listOf(
                    InfoItem("تويوتا كامري 2023", "أ ب ت 1234", "سارية"),
                    InfoItem("نيسان باترول 2021", "ك ع م 5678", "منتهية")
                )
            ),
            Section(
                title = "المخالفات المرورية",
                items = listOf(
                    InfoItem("لا توجد مخالفات", "سجلك نظيف، شكراً لالتزامك", null)
                )
            )
        )
        
        return DashboardData(services, sections)
    }

    private fun getBusinessData(): DashboardData {
        val services = listOf(
            Service("إصدار إقامة", ServiceIcon.DOCUMENT),
            Service("تجديد إقامة", ServiceIcon.CARD),
            Service("تأشيرات خروج وعودة", ServiceIcon.VISA),
            Service("نقل الخدمات", ServiceIcon.TRANSFER),
            Service("تعديل المهنة", ServiceIcon.USERS),
            Service("رخص العمل", ServiceIcon.CERTIFICATE),
            Service("الاستعلامات", ServiceIcon.SEARCH),
            Service("التقارير", ServiceIcon.CHART)
        )
        
        val sections = listOf(
            Section(
                title = "بيانات المنشأة",
                items = listOf(
                    InfoItem("السجل التجاري", "1010XXXXXX", "نشط"),
                    InfoItem("حالة النطاق", "البلاتيني", "ممتاز")
                )
            ),
            Section(
                title = "إحصائيات العمالة",
                items = listOf(
                    InfoItem("إجمالي العمالة", "45 موظف", null),
                    InfoItem("العمالة المتواجدة", "42 موظف", null)
                )
            ),
            Section(
                title = "الأرصدة",
                items = listOf(
                    InfoItem("رصيد المدفوعات", "12,500 ر.س", null),
                    InfoItem("رصيد الاستقدام", "2 تأشيرة", null)
                )
            )
        )
        
        return DashboardData(services, sections)
    }

    private fun getGovernmentData(): DashboardData {
        val services = listOf(
            Service("الاستعلام الشامل", ServiceIcon.SEARCH),
            Service("بيانات الموظفين", ServiceIcon.USERS),
            Service("المعاملات الإدارية", ServiceIcon.DOCUMENT),
            Service("المراسلات", ServiceIcon.CERTIFICATE),
            Service("مركبات الجهة", ServiceIcon.CAR),
            Service("تصاريح الدخول", ServiceIcon.LOCK),
            Service("التقارير الإحصائية", ServiceIcon.CHART),
            Service("الخدمات المرورية", ServiceIcon.TRAFFIC)
        )
        
        val sections = listOf(
            Section(
                title = "ملخص المعاملات",
                items = listOf(
                    InfoItem("المعاملات الواردة", "15 معاملة جديدة", "جديد"),
                    InfoItem("المعاملات المتأخرة", "2 معاملة", "عاجل")
                )
            ),
            Section(
                title = "التنبيهات الإدارية",
                items = listOf(
                    InfoItem("تعميم إداري", "بخصوص ساعات العمل في رمضان", "هام"),
                    InfoItem("تحديث بيانات", "مطلوب تحديث بيانات الموظفين", "للعلم")
                )
            )
        )
        
        return DashboardData(services, sections)
    }
}
