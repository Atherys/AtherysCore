* [33mcommit 1ba8413f72a6bcf9145aa5ae50fdc9c55ae69492[m[33m ([m[1;36mHEAD -> [m[1;32mmaster[m[33m, [m[1;33mtag: 1.13.2[m[33m, [m[1;31morigin/master[m[33m, [m[1;31morigin/HEAD[m[33m)[m
[31m|[m Author: haedhutner <mvvasilev@outlook.com>
[31m|[m Date:   Sun Feb 17 12:40:22 2019 +0200
[31m|[m 
[31m|[m     1.13.2:
[31m|[m     Remove EHCache
[31m|[m     Remove Cached annotation ( didn't even work )
[31m|[m     Add custom caching implementation
[31m|[m     Add CachedHibernateRepository
[31m|[m 
* [33mcommit a905fdf253fd95ee066c82f1683a96d5f0293ec9[m[33m ([m[1;33mtag: 1.13.1[m[33m)[m
[31m|[m Author: haedhutner <mvvasilev@outlook.com>
[31m|[m Date:   Sun Feb 17 00:27:46 2019 +0200
[31m|[m 
[31m|[m     Add EHCache and enable second-level caching by default
[31m|[m 
* [33mcommit c5c2749cd078539476c89310d680fc4d0878a714[m
[31m|[m Author: haedhutner <mvvasilev@outlook.com>
[31m|[m Date:   Fri Feb 15 22:54:20 2019 +0200
[31m|[m 
[31m|[m     Change flushCache to run on the main thread
[31m|[m 
* [33mcommit b9dd649bc71f79fd3933457cc53bec81843790ba[m[33m ([m[1;33mtag: 1.13.0[m[33m)[m
[31m|[m Author: haedhutner <mvvasilev@outlook.com>
[31m|[m Date:   Sun Feb 3 18:25:57 2019 +0200
[31m|[m 
[31m|[m     Add execute method to Repositories
[31m|[m 
* [33mcommit aa40b53deefe2acfe54430a2032f546c232e0bdb[m[33m ([m[1;33mtag: 1.12.7[m[33m)[m
[31m|[m Author: haedhutner <mvvasilev@outlook.com>
[31m|[m Date:   Sun Feb 3 13:01:34 2019 +0200
[31m|[m 
[31m|[m     Add getUniqueAccount to Economy API
[31m|[m   
*   [33mcommit 40a035881c117c82cf18e30cbd2a3c09a10eb404[m
[32m|[m[33m\[m  Merge: 17762a4 fd0b4e5
[32m|[m [33m|[m Author: haedhutner <mvvasilev@outlook.com>
[32m|[m [33m|[m Date:   Sun Feb 3 12:23:36 2019 +0200
[32m|[m [33m|[m 
[32m|[m [33m|[m     Merge branches 'hibernate' and 'master' of github.com:Atherys-Horizons/AtherysCore
[32m|[m [33m|[m   
[32m|[m *   [33mcommit fd0b4e5ec8261cbf3864e6781f44698b4a428f54[m[33m ([m[1;33mtag: 1.12.6[m[33m, [m[1;31morigin/hibernate[m[33m, [m[1;32mhibernate[m[33m)[m
[32m|[m [34m|[m[35m\[m  Merge: 1d80a49 5ca462f
[32m|[m [34m|[m [35m|[m Author: haedhutner <mvvasilev@outlook.com>
[32m|[m [34m|[m [35m|[m Date:   Sun Feb 3 12:19:36 2019 +0200
[32m|[m [34m|[m [35m|[m 
[32m|[m [34m|[m [35m|[m     Merge branch 'cacheless-repo' of github.com:Atherys-Horizons/AtherysCore into hibernate
[32m|[m [34m|[m [35m|[m     
[32m|[m [34m|[m [35m|[m     # Conflicts:
[32m|[m [34m|[m [35m|[m     #       src/main/java/com/atherys/core/db/HibernateRepository.java
[32m|[m [34m|[m [35m|[m 
[32m|[m [34m|[m * [33mcommit 5ca462f3720b2fc947a0b4be8d5d60a779b07630[m[33m ([m[1;31morigin/cacheless-repo[m[33m, [m[1;32mcacheless-repo[m[33m)[m
[32m|[m [34m|[m [35m|[m Author: haedhutner <mvvasilev@outlook.com>
[32m|[m [34m|[m [35m|[m Date:   Sat Feb 2 18:11:01 2019 +0200
[32m|[m [34m|[m [35m|[m 
[32m|[m [34m|[m [35m|[m     Remove HibernateRepository cache
[32m|[m [34m|[m [35m|[m     Modify TransformConverter
[32m|[m [34m|[m [35m|[m 
[32m|[m * [35m|[m [33mcommit 1d80a491d5a2c6e0d61f7d20fb6849179a3f5c21[m
[32m|[m [35m|[m [35m|[m Author: haedhutner <mvvasilev@outlook.com>
[32m|[m [35m|[m [35m|[m Date:   Sun Feb 3 12:16:11 2019 +0200
[32m|[m [35m|[m [35m|[m 
[32m|[m [35m|[m [35m|[m     Add Economy utilities
[32m|[m [35m|[m [35m|[m     Revert default HibernateRepository implementation to transactional
[32m|[m [35m|[m [35m|[m 
[32m|[m * [35m|[m [33mcommit ff1a72c17c66e3c0373de1b3984bfd083da5ec12[m
[32m|[m [35m|[m[35m/[m  Author: haedhutner <mvvasilev@outlook.com>
[32m|[m [35m|[m   Date:   Sat Feb 2 15:08:20 2019 +0200
[32m|[m [35m|[m   
[32m|[m [35m|[m       Create new HibernateRepository implementation
[32m|[m [35m|[m       Move old implementation to TransactionalHibernateRepository
[32m|[m [35m|[m 
[32m|[m * [33mcommit c28f9f530a3e68a054921259e8dd5c68ac4c38a8[m[33m ([m[1;33mtag: 1.12.5[m[33m)[m
[32m|[m [35m|[m Author: haedhutner <mvvasilev@outlook.com>
[32m|[m [35m|[m Date:   Wed Jan 30 22:56:13 2019 +0200
[32m|[m [35m|[m 
[32m|[m [35m|[m     Add async repository methods
[32m|[m [35m|[m 
[32m|[m * [33mcommit 00b9ae2da5a270c922521cafe0017ecad75b31f9[m[33m ([m[1;33mtag: 1.12.4[m[33m)[m
[32m|[m [35m|[m Author: haedhutner <mvvasilev@outlook.com>
[32m|[m [35m|[m Date:   Wed Jan 30 22:17:35 2019 +0200
[32m|[m [35m|[m 
[32m|[m [35m|[m     Repository save and delete methods will now returns CompletableFutures
[32m|[m [35m|[m     for chaining
[32m|[m [35m|[m 
[32m|[m * [33mcommit b262da3442ddb49dc98f126f25d30e5e21fce772[m[33m ([m[1;33mtag: 1.12.3[m[33m)[m
[32m|[m [35m|[m Author: Miroslav Vasilev <miroslav.vasilev@scalefocus.com>
[32m|[m [35m|[m Date:   Wed Jan 30 11:36:37 2019 +0200
[32m|[m [35m|[m 
[32m|[m [35m|[m     1.12.3:
[32m|[m [35m|[m     Add cacheAll() and flushCache() methods to HibernateRepository
[32m|[m [35m|[m 
[32m|[m * [33mcommit eb2116690ccbc016b573aff5e00daba69e1238b6[m[33m ([m[1;33mtag: 1.12.2[m[33m)[m
[32m|[m [35m|[m Author: Miroslav Vasilev <miroslav.vasilev@scalefocus.com>
[32m|[m [35m|[m Date:   Wed Jan 30 11:20:19 2019 +0200
[32m|[m [35m|[m 
[32m|[m [35m|[m     1.12.2:
[32m|[m [35m|[m     Add query methods with CriteriaQuery to Repository
[32m|[m [35m|[m 
[32m|[m * [33mcommit 0faea102db1fec95503ac1fdad2ab4d93aed722b[m[33m ([m[1;33mtag: 1.12.1[m[33m)[m
[32m|[m [35m|[m Author: Miroslav Vasilev <miroslav.vasilev@scalefocus.com>
[32m|[m [35m|[m Date:   Wed Jan 30 10:54:40 2019 +0200
[32m|[m [35m|[m 
[32m|[m [35m|[m     1.12.1:
[32m|[m [35m|[m     Change HibernateRepository#transactionOf to protected
[32m|[m [35m|[m 
[32m|[m * [33mcommit b459e79ae0817c74a4732bf49b3ce912c054b3c9[m[33m ([m[1;33mtag: 1.12.0[m[33m)[m
[32m|[m [35m|[m Author: Miroslav Vasilev <miroslav.vasilev@scalefocus.com>
[32m|[m [35m|[m Date:   Wed Jan 30 10:52:59 2019 +0200
[32m|[m [35m|[m 
[32m|[m [35m|[m     1.12.0:
[32m|[m [35m|[m     Implement HibernateRepository
[32m|[m [35m|[m 
[32m|[m * [33mcommit 2e18102d20523201c4e31125bb0a08a34d25560e[m
[32m|[m [35m|[m Author: haedhutner <mvvasilev@outlook.com>
[32m|[m [35m|[m Date:   Tue Jan 29 23:18:17 2019 +0200
[32m|[m [35m|[m 
[32m|[m [35m|[m     Remove AtherysRepository
[32m|[m [35m|[m     Add Repository interface
[32m|[m [35m|[m     Add HibernateRepository ( yet unimplemented )
[32m|[m [35m|[m     Identifiable ID now must extend Serializable
[32m|[m [35m|[m 
[32m|[m * [33mcommit 41d437531452b77601f661c4207399825a19578f[m
[32m|[m [35m|[m Author: haedhutner <mvvasilev@outlook.com>
[32m|[m [35m|[m Date:   Tue Jan 29 20:56:14 2019 +0200
[32m|[m [35m|[m 
[32m|[m [35m|[m     Refactor Repository implementation, and close EMF on server stopped event
[32m|[m [35m|[m 
[32m|[m * [33mcommit f614f3c91cbe54192370dfcd9b6066b5b2d9954b[m
[32m|[m [35m|[m Author: haedhutner <mvvasilev@outlook.com>
[32m|[m [35m|[m Date:   Sun Jan 27 21:00:14 2019 +0200
[32m|[m [35m|[m 
[32m|[m [35m|[m     Rework some repository methods' implementations
[32m|[m [35m|[m 
[32m|[m * [33mcommit ac982e382247775c89d59dcb1b240e77527568f5[m
[32m|[m [35m|[m Author: HaedHutner <mvvasilev@outlook.com>
[32m|[m [35m|[m Date:   Sun Jan 27 00:40:42 2019 +0200
[32m|[m [35m|[m 
[32m|[m [35m|[m     AtherysRepository is broken
[32m|[m [35m|[m   
[32m|[m *   [33mcommit d43570fca60f92af605a8fe00edb90f704215fa6[m[33m ([m[1;33mtag: 1.11.3[m[33m)[m
[32m|[m [36m|[m[1;31m\[m  Merge: a319a80 8bc872a
[32m|[m [36m|[m [1;31m|[m Author: Liam <liamreadsspam@gmail.com>
[32m|[m [36m|[m [1;31m|[m Date:   Sat Jan 26 11:53:00 2019 -0400
[32m|[m [36m|[m [1;31m|[m 
[32m|[m [36m|[m [1;31m|[m     Merge branch 'hibernate' of https://github.com/Atherys-Horizons/AtherysCore into hibernate
[32m|[m [36m|[m [1;31m|[m   
[32m|[m [36m|[m *   [33mcommit 8bc872aad9e180f7a0786b04f8d9bc890b5c60dd[m[33m ([m[1;33mtag: 1.11.2[m[33m)[m
[32m|[m [36m|[m [1;32m|[m[1;33m\[m  Merge: 0031524 c98e6cf
[32m|[m [36m|[m [1;32m|[m [1;33m|[m Author: Miroslav Vasilev <miroslav.vasilev@scalefocus.com>
[32m|[m [36m|[m [1;32m|[m [1;33m|[m Date:   Thu Jan 24 10:24:29 2019 +0200
[32m|[m [36m|[m [1;32m|[m [1;33m|[m 
[32m|[m [36m|[m [1;32m|[m [1;33m|[m     Merge branch 'hibernate' of https://github.com/Atherys-Horizons/AtherysCore into hibernate
[32m|[m [36m|[m [1;32m|[m [1;33m|[m 
[32m|[m [36m|[m * [1;33m|[m [33mcommit 003152474fcd36e00e2f836590427b791117bbe8[m
[32m|[m [36m|[m [1;33m|[m [1;33m|[m Author: Miroslav Vasilev <miroslav.vasilev@scalefocus.com>
[32m|[m [36m|[m [1;33m|[m [1;33m|[m Date:   Thu Jan 24 10:24:05 2019 +0200
[32m|[m [36m|[m [1;33m|[m [1;33m|[m 
[32m|[m [36m|[m [1;33m|[m [1;33m|[m     Make all fields of AtherysRepository protected
[32m|[m [36m|[m [1;33m|[m [1;33m|[m     Add CommandNotImplementedException
[32m|[m [36m|[m [1;33m|[m [1;33m|[m 
[32m|[m * [1;33m|[m [1;33m|[m [33mcommit a319a803a8864cd7ea4992041e5064c8d1478198[m
[32m|[m [1;33m|[m [1;33m|[m[1;33m/[m  Author: Liam <liamreadsspam@gmail.com>
[32m|[m [1;33m|[m[1;33m/[m[1;33m|[m   Date:   Sat Jan 26 11:52:47 2019 -0400
[32m|[m [1;33m|[m [1;33m|[m   
[32m|[m [1;33m|[m [1;33m|[m       Implement Transform converter
[32m|[m [1;33m|[m [1;33m|[m 
[32m|[m * [1;33m|[m [33mcommit c98e6cfe13230260877c7fe54e867836f44b7357[m
[32m|[m [1;33m|[m [1;33m|[m Author: Liam <liamreadsspam@gmail.com>
[32m|[m [1;33m|[m [1;33m|[m Date:   Mon Jan 21 16:45:33 2019 -0400
[32m|[m [1;33m|[m [1;33m|[m 
[32m|[m [1;33m|[m [1;33m|[m     Implement LocationConverter
[32m|[m [1;33m|[m [1;33m|[m   
[32m|[m * [1;33m|[m   [33mcommit 0304f733c9436ad0482d09017028464a64a7b1d7[m
[32m|[m [1;34m|[m[1;33m\[m [1;33m\[m  Merge: 51d4122 4e72c5f
[32m|[m [1;34m|[m [1;33m|[m[1;33m/[m  Author: Liam <liamreadsspam@gmail.com>
[32m|[m [1;34m|[m [1;33m|[m   Date:   Sun Jan 20 22:55:34 2019 -0400
[32m|[m [1;34m|[m [1;33m|[m   
[32m|[m [1;34m|[m [1;33m|[m       Merge branch 'hibernate' of https://github.com/Atherys-Horizons/AtherysCore into hibernate
[32m|[m [1;34m|[m [1;33m|[m 
[32m|[m [1;34m|[m * [33mcommit 4e72c5f73b48736aab732846f229a10a54b362cc[m[33m ([m[1;33mtag: 1.11.1[m[33m)[m
[32m|[m [1;34m|[m [1;35m|[m Author: Miroslav Vasilev <miroslav.vasilev@scalefocus.com>
[32m|[m [1;34m|[m [1;35m|[m Date:   Thu Jan 17 14:18:22 2019 +0200
[32m|[m [1;34m|[m [1;35m|[m 
[32m|[m [1;34m|[m [1;35m|[m     1.11.1:
[32m|[m [1;34m|[m [1;35m|[m     Add TemplateEngine:
[32m|[m [1;34m|[m [1;35m|[m         Allows for the creation of Text and BookView templates in the form
[32m|[m [1;34m|[m [1;35m|[m         of plugin asset files, which are parsed as Text objects.
[32m|[m [1;34m|[m [1;35m|[m 
[32m|[m [1;34m|[m * [33mcommit 0bdf850796cbd7badf8b233927118abd94d8ae99[m
[32m|[m [1;34m|[m [1;35m|[m Author: Miroslav Vasilev <miroslav.vasilev@scalefocus.com>
[32m|[m [1;34m|[m [1;35m|[m Date:   Thu Jan 17 10:26:59 2019 +0200
[32m|[m [1;34m|[m [1;35m|[m 
[32m|[m [1;34m|[m [1;35m|[m     Add beginning and ending argument to template replace
[32m|[m [1;34m|[m [1;35m|[m 
[32m|[m [1;34m|[m * [33mcommit 38ed9ed7ac2e5e71cf157ec8448cdf5896a8d22a[m[33m ([m[1;33mtag: 1.11.0[m[33m)[m
[32m|[m [1;34m|[m [1;35m|[m Author: Miroslav Vasilev <miroslav.vasilev@scalefocus.com>
[32m|[m [1;34m|[m [1;35m|[m Date:   Wed Jan 16 17:43:33 2019 +0200
[32m|[m [1;34m|[m [1;35m|[m 
[32m|[m [1;34m|[m [1;35m|[m     1.11.0:
[32m|[m [1;34m|[m [1;35m|[m     Remove View API
[32m|[m [1;34m|[m [1;35m|[m     Add Template API
[32m|[m [1;34m|[m [1;35m|[m     Add BookTemplate and TextTemplate implementations
[32m|[m [1;34m|[m [1;35m|[m     Add TextUtils
[32m|[m [1;34m|[m [1;35m|[m 
[32m|[m [1;34m|[m * [33mcommit fafc3031a32ece6182ef26ea5371f8747350de81[m
[32m|[m [1;34m|[m [1;35m|[m Author: Miroslav Vasilev <miroslav.vasilev@scalefocus.com>
[32m|[m [1;34m|[m [1;35m|[m Date:   Wed Jan 16 11:03:36 2019 +0200
[32m|[m [1;34m|[m [1;35m|[m 
[32m|[m [1;34m|[m [1;35m|[m     Add more command subtypes
[32m|[m [1;34m|[m [1;35m|[m 
[32m|[m * [1;35m|[m [33mcommit 51d4122506b1f7e46dbfdee6146d87045bc6f4be[m
[32m|[m [1;35m|[m[1;35m/[m  Author: Liam <liamreadsspam@gmail.com>
[32m|[m [1;35m|[m   Date:   Sun Jan 20 22:55:19 2019 -0400
[32m|[m [1;35m|[m   
[32m|[m [1;35m|[m       Implement Location converter
[32m|[m [1;35m|[m 
[32m|[m * [33mcommit e7637ffd333ac3539135a5ed8b3ac57548685a59[m
[32m|[m [1;35m|[m Author: haedhutner <mvvasilev@outlook.com>
[32m|[m [1;35m|[m Date:   Sun Jan 13 18:19:36 2019 +0200
[32m|[m [1;35m|[m 
[32m|[m [1;35m|[m     Add EMF to the Cause to get the damn event to trigger
[32m|[m [1;35m|[m 
[32m|[m * [33mcommit 9ec765d61c492c7de7e084ee68d7bca595e7f9f9[m
[32m|[m [1;35m|[m Author: haedhutner <mvvasilev@outlook.com>
[32m|[m [1;35m|[m Date:   Sun Jan 13 18:12:49 2019 +0200
[32m|[m [1;35m|[m 
[32m|[m [1;35m|[m     Update JPA to 2.1
[32m|[m [1;35m|[m 
[32m|[m * [33mcommit 74236c76a3abd796fb8d3c4dc7939294bd5cf72e[m[33m ([m[1;33mtag: 1.10.6[m[33m)[m
[32m|[m [1;35m|[m Author: haedhutner <mvvasilev@outlook.com>
[32m|[m [1;35m|[m Date:   Sun Jan 13 16:05:42 2019 +0200
[32m|[m [1;35m|[m 
[32m|[m [1;35m|[m     Trigger the Initialization event...
[32m|[m [1;35m|[m 
[32m|[m * [33mcommit a80f0d991ffaf1d047b4492c2228fd66fda6072f[m[33m ([m[1;33mtag: 1.10.5[m[33m)[m
[32m|[m [1;35m|[m Author: haedhutner <mvvasilev@outlook.com>
[32m|[m [1;35m|[m Date:   Sun Jan 13 15:59:25 2019 +0200
[32m|[m [1;35m|[m 
[32m|[m [1;35m|[m     v1.10.5:
[32m|[m [1;35m|[m     
[32m|[m [1;35m|[m     Add Spark library
[32m|[m [1;35m|[m     Add AtherysWebServer class for future abstraction over Spark
[32m|[m [1;35m|[m     Add AtherysHibernateInitializedEvent for when hibernate is done configuring and the EntityManagerFactory is available
[32m|[m [1;35m|[m     Remove createView() method from Viewable
[32m|[m [1;35m|[m 
[32m|[m * [33mcommit c3955c4b363e3083f5e155a421ab836488557c71[m
[32m|[m [1;35m|[m Author: HaedHutner <mvvasilev@outlook.com>
[32m|[m [1;35m|[m Date:   Sat Dec 22 21:55:41 2018 +0200
[32m|[m [1;35m|[m 
[32m|[m [1;35m|[m     Final touched on Hibernate abstraction
[32m|[m [1;35m|[m 
[32m|[m * [33mcommit b57caab6e9f0b03fd415f993bd2f3da85ea6eb4d[m
[32m|[m [1;35m|[m Author: HaedHutner <mvvasilev@outlook.com>
[32m|[m [1;35m|[m Date:   Sat Dec 22 19:56:32 2018 +0200
[32m|[m [1;35m|[m 
[32m|[m [1;35m|[m     Remove JUnit dependency
[32m|[m [1;35m|[m 
[32m|[m * [33mcommit eec85813d57e4fd4120a3af65ba71d34090e4bc0[m
[32m|[m [1;35m|[m Author: HaedHutner <mvvasilev@outlook.com>
[32m|[m [1;35m|[m Date:   Sat Dec 22 19:50:33 2018 +0200
[32m|[m [1;35m|[m 
[32m|[m [1;35m|[m     Update README
[32m|[m [1;35m|[m 
[32m|[m * [33mcommit 7d0703ef3a416053256a9f273da83239684bfcb4[m[33m ([m[1;33mtag: 1.10.4[m[33m)[m
[32m|[m [1;35m|[m Author: HaedHutner <mvvasilev@outlook.com>
[32m|[m [1;35m|[m Date:   Sat Dec 22 19:45:45 2018 +0200
[32m|[m [1;35m|[m 
[32m|[m [1;35m|[m     Refactor hibernate bootstrapping
[32m|[m [1;35m|[m 
[32m|[m * [33mcommit ab0564485fa01c8687b55f10f747df4ca8a58b7a[m
[32m|[m [1;35m|[m Author: Miroslav Vasilev <miroslav.vasilev@scalefocus.com>
[32m|[m [1;35m|[m Date:   Thu Nov 29 15:03:45 2018 +0200
[32m|[m [1;35m|[m 
[32m|[m [1;35m|[m     More work to be done on JPAConfig.
[32m|[m [1;35m|[m 
[32m|[m * [33mcommit e5e7e52b7d20c6ae4f27e2858015e9c02cfbcf75[m
[32m|[m [1;35m|[m Author: Miroslav Vasilev <miroslav.vasilev@scalefocus.com>
[32m|[m [1;35m|[m Date:   Wed Nov 28 16:25:51 2018 +0200
[32m|[m [1;35m|[m 
[32m|[m [1;35m|[m     More work to be done on JPAConfig.
[32m|[m [1;35m|[m 
[32m|[m * [33mcommit 021d9e6b022dc3d499b0a34bc09504f0964a3778[m[33m ([m[1;33mtag: 1.10.3[m[33m)[m
[32m|[m [1;35m|[m Author: Miroslav Vasilev <miroslav.vasilev@scalefocus.com>
[32m|[m [1;35m|[m Date:   Wed Nov 28 12:20:17 2018 +0200
[32m|[m [1;35m|[m 
[32m|[m [1;35m|[m     Add documentation, add methods for cache operations ( AtherysRepository )
[32m|[m [1;35m|[m 
[32m|[m * [33mcommit a8776155c7f491e070b5f83be1907bf46a712ae1[m[33m ([m[1;33mtag: 1.10.2[m[33m)[m
[32m|[m [1;35m|[m Author: Miroslav Vasilev <miroslav.vasilev@scalefocus.com>
[32m|[m [1;35m|[m Date:   Wed Nov 28 10:58:14 2018 +0200
[32m|[m [1;35m|[m 
[32m|[m [1;35m|[m     Make repository logger protected
[32m|[m [1;35m|[m 
[32m|[m * [33mcommit 606515b05523ce5ee6cd86cb6dfd2fddbd535ad7[m
[32m|[m [1;35m|[m Author: Miroslav Vasilev <miroslav.vasilev@scalefocus.com>
[32m|[m [1;35m|[m Date:   Wed Nov 28 10:51:09 2018 +0200
[32m|[m [1;35m|[m 
[32m|[m [1;35m|[m     Remove direct dependency on Sponge's identifiable. Instead, create custom parameterized Identifiable interface.
[32m|[m [1;35m|[m 
[32m|[m * [33mcommit 3cd6182815d64c86e0aa9a627c7bb214a13e211d[m
[32m|[m [1;35m|[m Author: Miroslav Vasilev <miroslav.vasilev@scalefocus.com>
[32m|[m [1;35m|[m Date:   Wed Nov 28 10:25:54 2018 +0200
[32m|[m [1;35m|[m 
[32m|[m [1;35m|[m     Improve AtherysRepository
[32m|[m [1;35m|[m 
[32m|[m * [33mcommit 94807382a312bbfde8ec64eb419b8dce55a39174[m
[32m|[m [1;35m|[m Author: Miroslav Vasilev <miroslav.vasilev@scalefocus.com>
[32m|[m [1;35m|[m Date:   Tue Nov 27 16:26:15 2018 +0200
[32m|[m [1;35m|[m 
[32m|[m [1;35m|[m     Improve AtherysRepository
[32m|[m [1;35m|[m 
[32m|[m * [33mcommit 3854cbf2bb05ce97454b9a5d5684b5534d684e12[m[33m ([m[1;33mtag: 1.10.1[m[33m)[m
[32m|[m [1;35m|[m Author: Miroslav Vasilev <miroslav.vasilev@scalefocus.com>
[32m|[m [1;35m|[m Date:   Tue Nov 27 15:10:48 2018 +0200
[32m|[m [1;35m|[m 
[32m|[m [1;35m|[m     Remove further old classes
[32m|[m [1;35m|[m 
[32m|[m * [33mcommit d2d4752186deeec8c214ff90fc7cef56dc27b6c8[m[33m ([m[1;33mtag: 1.10.0[m[33m)[m
[32m|[m [1;35m|[m Author: Miroslav Vasilev <miroslav.vasilev@scalefocus.com>
[32m|[m [1;35m|[m Date:   Tue Nov 27 14:22:11 2018 +0200
[32m|[m [1;35m|[m 
[32m|[m [1;35m|[m     Remove old database API and replace with Hibernate and JPA abstractions
[32m|[m [1;35m|[m 
* [1;35m|[m [33mcommit 17762a452cbec4cea7cd905c59c875fb354bf3c4[m
[1;35m|[m [1;35m|[m Author: Liam <liamreadsspam@gmail.com>
[1;35m|[m [1;35m|[m Date:   Tue Nov 27 22:02:16 2018 -0400
[1;35m|[m [1;35m|[m 
[1;35m|[m [1;35m|[m     Fix flowpowered link
[1;35m|[m [1;35m|[m 
* [1;35m|[m [33mcommit f707ebaa865ba75043f32098da8fb85934e1c9f8[m
[1;35m|[m [1;35m|[m Author: Liam <liamreadsspam@gmail.com>
[1;35m|[m [1;35m|[m Date:   Tue Nov 27 21:38:09 2018 -0400
[1;35m|[m [1;35m|[m 
[1;35m|[m [1;35m|[m     Add links to javadoc
[1;35m|[m [1;35m|[m 
* [1;35m|[m [33mcommit 94889ec8e7830d148345bf54b3f49ef5108f98d0[m
[1;35m|[m[1;35m/[m  Author: Liam <32738636+LJNIC@users.noreply.github.com>
[1;35m|[m   Date:   Tue Nov 27 20:21:58 2018 -0400
[1;35m|[m   
[1;35m|[m       Update build.gradle
[1;35m|[m   
*   [33mcommit 1053c4653a1c34783cf0b05538eb67ec1c9720f5[m[33m ([m[1;33mtag: 1.9.0[m[33m)[m
[1;36m|[m[31m\[m  Merge: 79769b6 89eafd7
[1;36m|[m [31m|[m Author: Liam <liamreadsspam@gmail.com>
[1;36m|[m [31m|[m Date:   Mon Nov 26 23:07:47 2018 -0400
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     Merge branch 'master' of https://github.com/Atherys-Horizons/AtherysCore
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit 89eafd7636c07c4d90b0d58f99c36ce75c32fad3[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Sat Sep 15 20:53:29 2018 +0300
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     Custom TypeSerializers do not work.
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit 5336ef6a14c337c54ff8ce48f8c8bb64d9dc104f[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Sat Sep 15 20:34:42 2018 +0300
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     Previous approach was not going to work. Try this instead.
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit 044278042cb09a32e5bd8ccbe339d825916ddcec[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Sat Sep 15 20:05:58 2018 +0300
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     Oops fix generics
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit efaa5197e58a5493b6f3bd2b6d8b8e2fcb51553b[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Sat Sep 15 20:01:58 2018 +0300
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     Added custom serializer support in PluginConfig
[1;36m|[m [31m|[m 
* [31m|[m [33mcommit 79769b6cbc641db3da474c9875cf868b2f563f48[m
[31m|[m[31m/[m  Author: Liam <liamreadsspam@gmail.com>
[31m|[m   Date:   Mon Nov 26 23:07:31 2018 -0400
[31m|[m   
[31m|[m       Add javadoc task to build
[31m|[m   
*   [33mcommit 6192a3632bc6f5833bc3f62e410912e97a2c7077[m
[32m|[m[33m\[m  Merge: ebe3fd5 718f272
[32m|[m [33m|[m Author: HaedHutner <mvvasilev@outlook.com>
[32m|[m [33m|[m Date:   Wed Aug 22 20:10:42 2018 +0300
[32m|[m [33m|[m 
[32m|[m [33m|[m     Merge pull request #16 from Atherys-Horizons/interaction-service
[32m|[m [33m|[m     
[32m|[m [33m|[m     Interaction service
[32m|[m [33m|[m 
[32m|[m * [33mcommit 718f272d48782582f645aa35efdd8901059c9947[m[33m ([m[1;31morigin/interaction-service[m[33m)[m
[32m|[m [33m|[m Author: Liam <liamreadsspam@gmail.com>
[32m|[m [33m|[m Date:   Wed Aug 22 12:28:28 2018 -0300
[32m|[m [33m|[m 
[32m|[m [33m|[m     More abstraction
[32m|[m [33m|[m 
[32m|[m * [33mcommit d83b4c1eaf7f57fcd96812ddc8698751605d9959[m
[32m|[m[32m/[m  Author: Liam <liamreadsspam@gmail.com>
[32m|[m   Date:   Wed Aug 22 11:46:50 2018 -0300
[32m|[m   
[32m|[m       Interaction service abstraction
[32m|[m 
* [33mcommit ebe3fd5fcbb3941104f85aebc35163c1ee2fe020[m
[33m|[m Author: HaedHutner <mvvasilev@outlook.com>
[33m|[m Date:   Sun Aug 12 11:08:55 2018 +0300
[33m|[m 
[33m|[m     Did it the wrong way around
[33m|[m 
* [33mcommit 72cc3b10d4de50fc8cbfc16a341f1383b8c33568[m
[33m|[m Author: HaedHutner <mvvasilev@outlook.com>
[33m|[m Date:   Sun Aug 12 10:10:02 2018 +0300
[33m|[m 
[33m|[m     DBObject now extends Identifiable.
[33m|[m   
*   [33mcommit 5dcf457747b94dccc0b1e38c095def7e7661261c[m
[34m|[m[35m\[m  Merge: e38bf5b 4c53597
[34m|[m [35m|[m Author: HaedHutner <mvvasilev@outlook.com>
[34m|[m [35m|[m Date:   Thu Aug 9 14:29:57 2018 +0300
[34m|[m [35m|[m 
[34m|[m [35m|[m     Merge pull request #15 from Atherys-Horizons/pagination
[34m|[m [35m|[m     
[34m|[m [35m|[m     Pagination 2.0, with Lists and stuff
[34m|[m [35m|[m 
[34m|[m * [33mcommit 4c53597302f59b4b70b3c025204dbb4c983cd12f[m
[34m|[m [35m|[m Author: Liam <liamreadsspam@gmail.com>
[34m|[m [35m|[m Date:   Wed Aug 8 15:53:07 2018 -0300
[34m|[m [35m|[m 
[34m|[m [35m|[m     Add pagination utility for help commands
[34m|[m [35m|[m   
* [35m|[m   [33mcommit e38bf5bb773c98400850da0268e9557f1db6a949[m
[36m|[m[35m\[m [35m\[m  Merge: 69639d7 1e1b2de
[36m|[m [35m|[m[35m/[m  Author: HaedHutner <mvvasilev@outlook.com>
[36m|[m [35m|[m   Date:   Thu Aug 2 17:56:06 2018 +0300
[36m|[m [35m|[m   
[36m|[m [35m|[m       Merge pull request #13 from LJNIC/sound-utils
[36m|[m [35m|[m       
[36m|[m [35m|[m       Add sound utilities to easily create and play sounds
[36m|[m [35m|[m 
[36m|[m * [33mcommit 1e1b2deb0f455d6bd725ed644c0533794bd1133d[m
[36m|[m[36m/[m  Author: Liam <liamreadsspam@gmail.com>
[36m|[m   Date:   Thu Aug 2 11:26:53 2018 -0300
[36m|[m   
[36m|[m       Add sound utilities to easily create and play sounds
[36m|[m 
* [33mcommit 69639d7d74e22f053e3a2353e3cf8b63c1d770e5[m
[1;31m|[m Author: Haed Hutner <mvvasilev@outlook.com>
[1;31m|[m Date:   Sun Jul 15 09:35:13 2018 +0300
[1;31m|[m 
[1;31m|[m     Build against spongeapi 7.0.0
[1;31m|[m 
* [33mcommit ac36c5d88bd512d056eef61821bccd00e2041bd1[m
[1;31m|[m Author: Haed Hutner <mvvasilev@outlook.com>
[1;31m|[m Date:   Sun Jul 15 01:10:28 2018 +0300
[1;31m|[m 
[1;31m|[m     Changed README
[1;31m|[m 
* [33mcommit 918c882adb3291c93322419a952a12237d133bca[m[33m ([m[1;33mtag: v1.3.0[m[33m)[m
[1;31m|[m Author: Haed Hutner <mvvasilev@outlook.com>
[1;31m|[m Date:   Sun Jul 15 00:45:14 2018 +0300
[1;31m|[m 
[1;31m|[m     Upped to version 1.3.0
[1;31m|[m 
* [33mcommit 5aaac0527d9ac057460286c6aea9463e194f80f8[m
[1;31m|[m Author: Haed Hutner <mvvasilev@outlook.com>
[1;31m|[m Date:   Sun Jul 15 00:42:58 2018 +0300
[1;31m|[m 
[1;31m|[m     Removed dependency on Hibernate. Another day, maybe
[1;31m|[m 
* [33mcommit f3ba381afc0497b8339342dd796e1c31640df3c1[m
[1;31m|[m Author: Haed Hutner <mvvasilev@outlook.com>
[1;31m|[m Date:   Sun Jul 15 00:08:52 2018 +0300
[1;31m|[m 
[1;31m|[m     Ripped out damage stuff, turning those into their own plugin as well
[1;31m|[m 
* [33mcommit dcc9d86e84b59cdd8073a302073f10adbd93049e[m
[1;31m|[m Author: Haed Hutner <mvvasilev@outlook.com>
[1;31m|[m Date:   Sat Jul 14 23:44:20 2018 +0300
[1;31m|[m 
[1;31m|[m     Ripped out parties as well, will turn those into their own plugin
[1;31m|[m 
* [33mcommit 8218f7d6f93891db0d891c9545b401805c8254b8[m
[1;31m|[m Author: Haed Hutner <mvvasilev@outlook.com>
[1;31m|[m Date:   Sat Jul 14 16:54:41 2018 +0300
[1;31m|[m 
[1;31m|[m     Scripting has no business being in AtherysCore either. Another plugin instead.
[1;31m|[m   
*   [33mcommit db3f4a5c0750a913a1e9ea9ad7794669eb25dcae[m
[1;32m|[m[1;33m\[m  Merge: 65ac7f8 3049900
[1;32m|[m [1;33m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;32m|[m [1;33m|[m Date:   Sun Jul 1 17:23:14 2018 +0300
[1;32m|[m [1;33m|[m 
[1;32m|[m [1;33m|[m     Merge pull request #12 from LJNIC/entity-functions
[1;32m|[m [1;33m|[m     
[1;32m|[m [1;33m|[m     Entity functions
[1;32m|[m [1;33m|[m 
[1;32m|[m * [33mcommit 3049900c28cc7b2c7bb8f8ff14629eee673cdf9c[m
[1;32m|[m [1;33m|[m Author: LJNIC <liamreadsspam@gmail.com>
[1;32m|[m [1;33m|[m Date:   Sun Jul 1 11:02:10 2018 -0300
[1;32m|[m [1;33m|[m 
[1;32m|[m [1;33m|[m     Simplify I/O
[1;32m|[m [1;33m|[m   
[1;32m|[m *   [33mcommit bfca16142b14ffcdd1fd63d87a12fcb28024604e[m
[1;32m|[m [1;34m|[m[1;35m\[m  Merge: edad73f 4b196de
[1;32m|[m [1;34m|[m [1;35m|[m Author: LJNIC <liamreadsspam@gmail.com>
[1;32m|[m [1;34m|[m [1;35m|[m Date:   Sat Jun 30 11:54:09 2018 -0300
[1;32m|[m [1;34m|[m [1;35m|[m 
[1;32m|[m [1;34m|[m [1;35m|[m     Merge branch 'master' of https://github.com/LJNIC/AtherysCore
[1;32m|[m [1;34m|[m [1;35m|[m 
[1;32m|[m [1;34m|[m * [33mcommit 4b196de281b794a395bf40b4e306ceff300eeeef[m
[1;32m|[m [1;34m|[m [1;35m|[m Author: LJNIC <liamjnicolle@gmail.com>
[1;32m|[m [1;34m|[m [1;35m|[m Date:   Mon Apr 30 11:07:45 2018 -0300
[1;32m|[m [1;34m|[m [1;35m|[m 
[1;32m|[m [1;34m|[m [1;35m|[m     Tell player when invitee is not online
[1;32m|[m [1;34m|[m [1;35m|[m 
[1;32m|[m * [1;35m|[m [33mcommit edad73f6226ed8287026803ffc5cf87327b89f5c[m
[1;32m|[m[1;32m/[m [1;35m/[m  Author: LJNIC <liamreadsspam@gmail.com>
[1;32m|[m [1;35m|[m   Date:   Sat Jun 30 11:52:36 2018 -0300
[1;32m|[m [1;35m|[m   
[1;32m|[m [1;35m|[m       Actually add entity script functions
[1;32m|[m [1;35m|[m   
* [1;35m|[m   [33mcommit 65ac7f81ff4c48ccc7973f09259d0df7bed8fbe6[m
[1;36m|[m[31m\[m [1;35m\[m  Merge: d0b41f7 106f227
[1;36m|[m [31m|[m [1;35m|[m Author: LJNIC <liamreadsspam@gmail.com>
[1;36m|[m [31m|[m [1;35m|[m Date:   Sat Jun 30 11:28:52 2018 -0300
[1;36m|[m [31m|[m [1;35m|[m 
[1;36m|[m [31m|[m [1;35m|[m     Add entity script functions
[1;36m|[m [31m|[m [1;35m|[m 
[1;36m|[m * [1;35m|[m [33mcommit 106f227092cb6065f869f01ba492bb878ccac502[m
[1;36m|[m [31m|[m [1;35m|[m Author: Haed Hutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m [1;35m|[m Date:   Thu Jun 28 17:48:37 2018 +0300
[1;36m|[m [31m|[m [1;35m|[m 
[1;36m|[m [31m|[m [1;35m|[m     Fix duration formatting
[1;36m|[m [31m|[m [1;35m|[m 
[1;36m|[m * [1;35m|[m [33mcommit 6d5826b9202f3b6766a77845c766440c6af81efe[m
[1;36m|[m [31m|[m [1;35m|[m Author: Haed Hutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m [1;35m|[m Date:   Thu Jun 28 17:43:46 2018 +0300
[1;36m|[m [31m|[m [1;35m|[m 
[1;36m|[m [31m|[m [1;35m|[m     '/srun' will now display the duration of execution
[1;36m|[m [31m|[m [1;35m|[m 
[1;36m|[m * [1;35m|[m [33mcommit 4ff5a7f6f5fb59a707d866b300865948416e999a[m
[1;36m|[m [31m|[m [1;35m|[m Author: Haed Hutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m [1;35m|[m Date:   Thu Jun 28 17:34:39 2018 +0300
[1;36m|[m [31m|[m [1;35m|[m 
[1;36m|[m [31m|[m [1;35m|[m     Access the ScriptEngine using the getter, thanks
[1;36m|[m [31m|[m [1;35m|[m 
[1;36m|[m * [1;35m|[m [33mcommit e600e8172a8190d35ea71e4e3ffebf63f2f16bf0[m
[1;36m|[m [31m|[m [1;35m|[m Author: Haed Hutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m [1;35m|[m Date:   Thu Jun 28 17:30:23 2018 +0300
[1;36m|[m [31m|[m [1;35m|[m 
[1;36m|[m [31m|[m [1;35m|[m     '/srun' will print a stacktrace if the error message is null
[1;36m|[m [31m|[m [1;35m|[m 
[1;36m|[m * [1;35m|[m [33mcommit f1547f40ecd5c390c59b62df446cb4b3c0099094[m
[1;36m|[m [31m|[m [1;35m|[m Author: Haed Hutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m [1;35m|[m Date:   Thu Jun 28 17:23:05 2018 +0300
[1;36m|[m [31m|[m [1;35m|[m 
[1;36m|[m [31m|[m [1;35m|[m     '/srun' command can now be used on the console
[1;36m|[m [31m|[m [1;35m|[m 
[1;36m|[m * [1;35m|[m [33mcommit e0e77bc0d3909c578d6d5ebbfa0af4f19fd7baf4[m
[1;36m|[m [31m|[m [1;35m|[m Author: Haed Hutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m [1;35m|[m Date:   Thu Jun 28 17:14:30 2018 +0300
[1;36m|[m [31m|[m [1;35m|[m 
[1;36m|[m [31m|[m [1;35m|[m     Fix generics for AbstractMongoDatabaseManager#toDocument()
[1;36m|[m [31m|[m [1;35m|[m 
[1;36m|[m * [1;35m|[m [33mcommit f299be36ae0241f1e1689998d5f0da24365908cd[m
[1;36m|[m [31m|[m [1;35m|[m Author: Haed Hutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m [1;35m|[m Date:   Thu Jun 28 17:10:40 2018 +0300
[1;36m|[m [31m|[m [1;35m|[m 
[1;36m|[m [31m|[m [1;35m|[m     Added event for script library registration
[1;36m|[m [31m|[m [1;35m|[m 
[1;36m|[m * [1;35m|[m [33mcommit 88665d7943f511e7249b1dcff16782834a95b77e[m
[1;36m|[m [31m|[m [1;35m|[m Author: Haed Hutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m [1;35m|[m Date:   Thu Jun 28 17:02:51 2018 +0300
[1;36m|[m [31m|[m [1;35m|[m 
[1;36m|[m [31m|[m [1;35m|[m     Moved Scripting to AtherysCore
[1;36m|[m [31m|[m [1;35m|[m   
[1;36m|[m * [1;35m|[m   [33mcommit b1da1e5ae96419a60804e498e5cf9d88cb0c1138[m
[1;36m|[m [32m|[m[33m\[m [1;35m\[m  Merge: fdf4eb1 7a8d655
[1;36m|[m [32m|[m [33m|[m [1;35m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [32m|[m [33m|[m [1;35m|[m Date:   Thu Jun 28 15:56:45 2018 +0300
[1;36m|[m [32m|[m [33m|[m [1;35m|[m 
[1;36m|[m [32m|[m [33m|[m [1;35m|[m     Merge pull request #11 from Atherys-Horizons/party-refactor
[1;36m|[m [32m|[m [33m|[m [1;35m|[m     
[1;36m|[m [32m|[m [33m|[m [1;35m|[m     Party refactor
[1;36m|[m [32m|[m [33m|[m [1;35m|[m 
[1;36m|[m [32m|[m * [1;35m|[m [33mcommit 7a8d65521405a8c56cdf6c56b2eaadbbc04c71d5[m[33m ([m[1;31morigin/party-refactor[m[33m)[m
[1;36m|[m [32m|[m [33m|[m [1;35m|[m Author: Haed Hutner <mvvasilev@outlook.com>
[1;36m|[m [32m|[m [33m|[m [1;35m|[m Date:   Sat Jun 23 19:55:17 2018 +0300
[1;36m|[m [32m|[m [33m|[m [1;35m|[m 
[1;36m|[m [32m|[m [33m|[m [1;35m|[m     DamageConfig will now automatically remove projectile when replacing the damage source
[1;36m|[m [32m|[m [33m|[m [1;35m|[m 
[1;36m|[m [32m|[m * [1;35m|[m [33mcommit fef2a69cef7676d7fc97ed54e06cd5956915209e[m
[1;36m|[m [32m|[m [33m|[m [1;35m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [32m|[m [33m|[m [1;35m|[m Date:   Tue May 29 19:09:58 2018 +0300
[1;36m|[m [32m|[m [33m|[m [1;35m|[m 
[1;36m|[m [32m|[m [33m|[m [1;35m|[m     Adding extra abstraction for MongoDB
[1;36m|[m [32m|[m [33m|[m [1;35m|[m 
[1;36m|[m [32m|[m * [1;35m|[m [33mcommit 6af3f123a1683549a889bec86f9ed8c61d959fd1[m
[1;36m|[m [32m|[m [33m|[m [1;35m|[m Author: Haed Hutner <mvvasilev@outlook.com>
[1;36m|[m [32m|[m [33m|[m [1;35m|[m Date:   Thu May 24 17:20:47 2018 +0300
[1;36m|[m [32m|[m [33m|[m [1;35m|[m 
[1;36m|[m [32m|[m [33m|[m [1;35m|[m     Now parsing Json from Gson as a BasicDBObject
[1;36m|[m [32m|[m [33m|[m [1;35m|[m 
[1;36m|[m [32m|[m * [1;35m|[m [33mcommit 4c0abebdd48b994391f24059eb378b1a342e4801[m
[1;36m|[m [32m|[m [33m|[m [1;35m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [32m|[m [33m|[m [1;35m|[m Date:   Wed May 23 09:12:37 2018 +0300
[1;36m|[m [32m|[m [33m|[m [1;35m|[m 
[1;36m|[m [32m|[m [33m|[m [1;35m|[m     Added protected getter for Morphia instance
[1;36m|[m [32m|[m [33m|[m [1;35m|[m 
[1;36m|[m [32m|[m * [1;35m|[m [33mcommit 6b6bcc15e5330653d3288a3b82fa4e5d1c9a5d97[m
[1;36m|[m [32m|[m [33m|[m [1;35m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [32m|[m [33m|[m [1;35m|[m Date:   Wed May 23 08:44:15 2018 +0300
[1;36m|[m [32m|[m [33m|[m [1;35m|[m 
[1;36m|[m [32m|[m [33m|[m [1;35m|[m     Introduced GsonTypeConverter for easier converting of 3rd party classes to DBObject(s)
[1;36m|[m [32m|[m [33m|[m [1;35m|[m 
[1;36m|[m [32m|[m * [1;35m|[m [33mcommit abef358a22188d62f5c3704ce68e4dfa397885f6[m
[1;36m|[m [32m|[m [33m|[m [1;35m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [32m|[m [33m|[m [1;35m|[m Date:   Sun May 20 20:03:11 2018 +0300
[1;36m|[m [32m|[m [33m|[m [1;35m|[m 
[1;36m|[m [32m|[m [33m|[m [1;35m|[m     Added toggleable pvp block for parties
[1;36m|[m [32m|[m [33m|[m [1;35m|[m 
[1;36m|[m [32m|[m * [1;35m|[m [33mcommit 1ee546e0e5981afc4ef7c1c013eae8c28e21e125[m
[1;36m|[m [32m|[m [33m|[m [1;35m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [32m|[m [33m|[m [1;35m|[m Date:   Sun May 20 19:48:03 2018 +0300
[1;36m|[m [32m|[m [33m|[m [1;35m|[m 
[1;36m|[m [32m|[m [33m|[m [1;35m|[m     Added more messages to commands
[1;36m|[m [32m|[m [33m|[m [1;35m|[m 
[1;36m|[m [32m|[m * [1;35m|[m [33mcommit 7a93376ed45b654b416b074a8e6b1af79fda63b4[m
[1;36m|[m [32m|[m [33m|[m [1;35m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [32m|[m [33m|[m [1;35m|[m Date:   Sun May 20 18:44:12 2018 +0300
[1;36m|[m [32m|[m [33m|[m [1;35m|[m 
[1;36m|[m [32m|[m [33m|[m [1;35m|[m     Fixed issues with persistence
[1;36m|[m [32m|[m [33m|[m [1;35m|[m 
[1;36m|[m [32m|[m * [1;35m|[m [33mcommit 40ad840fbca34e46ae20c0d03299cfa0b9d9c62d[m
[1;36m|[m [32m|[m [33m|[m [1;35m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [32m|[m [33m|[m [1;35m|[m Date:   Sun May 20 17:35:58 2018 +0300
[1;36m|[m [32m|[m [33m|[m [1;35m|[m 
[1;36m|[m [32m|[m [33m|[m [1;35m|[m     Fix for PartyManager#getUserParty(User)
[1;36m|[m [32m|[m [33m|[m [1;35m|[m 
[1;36m|[m [32m|[m * [1;35m|[m [33mcommit 112de0266d57aa99cc5294949a55340eb9d8a109[m
[1;36m|[m [32m|[m [33m|[m [1;35m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [32m|[m [33m|[m [1;35m|[m Date:   Sun May 20 17:28:22 2018 +0300
[1;36m|[m [32m|[m [33m|[m [1;35m|[m 
[1;36m|[m [32m|[m [33m|[m [1;35m|[m     Placed invite command checks _before_ the relevant code...
[1;36m|[m [32m|[m [33m|[m [1;35m|[m 
[1;36m|[m [32m|[m * [1;35m|[m [33mcommit b626f1bede7ae46551121c67cac3271804b95e5b[m
[1;36m|[m [32m|[m [33m|[m [1;35m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [32m|[m [33m|[m [1;35m|[m Date:   Sun May 20 17:20:51 2018 +0300
[1;36m|[m [32m|[m [33m|[m [1;35m|[m 
[1;36m|[m [32m|[m [33m|[m [1;35m|[m     Added some error messages to party invite
[1;36m|[m [32m|[m [33m|[m [1;35m|[m 
[1;36m|[m [32m|[m * [1;35m|[m [33mcommit 1a0b55d3d9c0a70b4ba4edf0c599c19150316199[m
[1;36m|[m [32m|[m[32m/[m [1;35m/[m  Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [32m|[m [1;35m|[m   Date:   Sun May 20 16:55:33 2018 +0300
[1;36m|[m [32m|[m [1;35m|[m   
[1;36m|[m [32m|[m [1;35m|[m       Party Refactor:
[1;36m|[m [32m|[m [1;35m|[m       - Now stores the Party UUID as custom data on to the User
[1;36m|[m [32m|[m [1;35m|[m       - PartyManager completely refactored
[1;36m|[m [32m|[m [1;35m|[m       - Introduced Morphia ORM support
[1;36m|[m [32m|[m [1;35m|[m       - Deprecated old MongoDB Java Driver abstractions
[1;36m|[m [32m|[m [1;35m|[m       - Created new Morphia ORM abstractions
[1;36m|[m [32m|[m [1;35m|[m 
[1;36m|[m * [1;35m|[m [33mcommit fdf4eb1a4adc2ce28e6f4664d2fcdee2f5d7a43b[m
[1;36m|[m [33m|[m [1;35m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [33m|[m [1;35m|[m Date:   Sun May 20 10:45:35 2018 +0300
[1;36m|[m [33m|[m [1;35m|[m 
[1;36m|[m [33m|[m [1;35m|[m     Now compiles with MongoDB Morphia instead of the raw Mongo Java Driver
[1;36m|[m [33m|[m [1;35m|[m 
[1;36m|[m * [1;35m|[m [33mcommit d1099e39d0f9b7e5a996fef036f1393152041de7[m
[1;36m|[m [33m|[m [1;35m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [33m|[m [1;35m|[m Date:   Sat May 19 08:56:25 2018 +0300
[1;36m|[m [33m|[m [1;35m|[m 
[1;36m|[m [33m|[m [1;35m|[m     Code Reformat
[1;36m|[m [33m|[m [1;35m|[m     Added static method for more easily accessing the CommandService via AtherysCore.getCommandService()
[1;36m|[m [33m|[m [1;35m|[m 
[1;36m|[m * [1;35m|[m [33mcommit 41ec47245eaa0fa93b32329ed35bd5d4bf302b50[m
[1;36m|[m [33m|[m [1;35m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [33m|[m [1;35m|[m Date:   Fri May 18 21:44:36 2018 +0300
[1;36m|[m [33m|[m [1;35m|[m 
[1;36m|[m [33m|[m [1;35m|[m     Add method for registering multiple subtypes
[1;36m|[m [33m|[m [1;35m|[m 
[1;36m|[m * [1;35m|[m [33mcommit f70b38a3ecf7cba420b85658be85ec2e9e4e4caa[m
[1;36m|[m [33m|[m [1;35m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [33m|[m [1;35m|[m Date:   Fri May 18 21:14:05 2018 +0300
[1;36m|[m [33m|[m [1;35m|[m 
[1;36m|[m [33m|[m [1;35m|[m     Version up to 1.2.3:
[1;36m|[m [33m|[m [1;35m|[m     - Created TypeAdapterFactoryRegistry
[1;36m|[m [33m|[m [1;35m|[m 
* [33m|[m [1;35m|[m [33mcommit d0b41f79919d530b3b733c85895210567bfa29fd[m
[33m|[m[33m/[m [1;35m/[m  Author: LJNIC <liamjnicolle@gmail.com>
[33m|[m [1;35m|[m   Date:   Mon May 21 17:15:11 2018 -0300
[33m|[m [1;35m|[m   
[33m|[m [1;35m|[m       Adapter fix
[33m|[m [1;35m|[m 
* [1;35m|[m [33mcommit 42afcf0e8eeea98641214d3560acd162e64d9299[m
[33m|[m [1;35m|[m Author: Haed Hutner <mvvasilev@outlook.com>
[33m|[m [1;35m|[m Date:   Mon May 7 12:27:13 2018 +0300
[33m|[m [1;35m|[m 
[33m|[m [1;35m|[m     Added an annotation command API ( Addresses Issue #10 )
[33m|[m [1;35m|[m     Reformatted to meet the Google Java Style Guide
[33m|[m [1;35m|[m 
* [1;35m|[m [33mcommit ad269af476d1369f34d67f498877c9213f7882ae[m
[1;35m|[m[1;35m/[m  Author: HaedHutner <mvvasilev@outlook.com>
[1;35m|[m   Date:   Tue May 1 10:54:49 2018 +0300
[1;35m|[m   
[1;35m|[m       Now shadowing in Hibernate JPA API
[1;35m|[m 
* [33mcommit 8e689eca1cab4d1a78b4efd64e0e9abfba3a2c5a[m
[33m|[m Author: Haed Hutner <mvvasilev@outlook.com>
[33m|[m Date:   Sat Apr 28 00:38:00 2018 +0300
[33m|[m 
[33m|[m     Now shadowing in Hibernate instead of ORMLite
[33m|[m 
* [33mcommit 7c76f3d3fc1ec83b531cd1f2d125ea4dbfbd6d90[m
[33m|[m Author: Haed Hutner <mvvasilev@outlook.com>
[33m|[m Date:   Fri Apr 6 16:47:39 2018 +0300
[33m|[m 
[33m|[m     Added Atherys Damage Toggle to Config
[33m|[m 
* [33mcommit ea1c565dbd0c090e3fdf9b1e12cb297ff28b9d50[m
[33m|[m Author: Haed Hutner <mvvasilev@outlook.com>
[33m|[m Date:   Fri Mar 30 22:55:09 2018 +0300
[33m|[m 
[33m|[m     There is no need for Viewable to return an Optional.
[33m|[m     Also, removed View abstract classes. They don't wor anyway.
[33m|[m 
* [33mcommit 6f578f62aec67c0f7620dc0ee16eea98aa7a8044[m
[33m|[m Author: Haed Hutner <mvvasilev@outlook.com>
[33m|[m Date:   Sun Mar 25 22:02:29 2018 +0300
[33m|[m 
[33m|[m     AtherysIndirectEntityDamageSource made redundant
[33m|[m     Because I can't figure it out.
[33m|[m 
* [33mcommit 136d48a32efa89396df69753e791e47144d3db36[m
[33m|[m Author: Haed Hutner <mvvasilev@outlook.com>
[33m|[m Date:   Sun Mar 25 21:53:55 2018 +0300
[33m|[m 
[33m|[m     Forgot to add a primitive type to the damage source builder
[33m|[m 
* [33mcommit 8439a63e0c32fe7a264252667bfcad96ecb681e9[m
[33m|[m Author: Haed Hutner <mvvasilev@outlook.com>
[33m|[m Date:   Sun Mar 25 21:46:35 2018 +0300
[33m|[m 
[33m|[m     That last fix didn't work.
[33m|[m     
[33m|[m     Instead, refactoring AtherysDamageSources.
[33m|[m 
* [33mcommit 075a20563ee5f190f61026531d3427106fe5262a[m
[33m|[m Author: Haed Hutner <mvvasilev@outlook.com>
[33m|[m Date:   Sun Mar 25 17:18:52 2018 +0300
[33m|[m 
[33m|[m     Hopefully a fix for damage-related errors.
[33m|[m     
[33m|[m     AtherysCore no longer attempts to extend the Sponge damage-related interfaces and classes to do with DamageSource. Instead, only custom DamageTypes are provided. When a DamageEvent is detected, the DamageConfig is looked up by the ItemType of the item in the player's hand, and the appropriate DamageType is appended to the DamageEntityEvent's Cause.
[33m|[m 
* [33mcommit cf170c4c057760a923d770363ef4ef6b669502cf[m
[33m|[m Author: Haed Hutner <mvvasilev@outlook.com>
[33m|[m Date:   Sat Mar 24 20:33:36 2018 +0200
[33m|[m 
[33m|[m     Add Question#register()
[33m|[m 
* [33mcommit 2af0961f4fa8c8b279ab3173103b7f7c40977833[m
[33m|[m Author: Haed Hutner <mvvasilev@outlook.com>
[33m|[m Date:   Sat Mar 24 20:08:35 2018 +0200
[33m|[m 
[33m|[m     tfw you forget to return the builder instance for chaining
[33m|[m 
* [33mcommit c96c295162aba4223c5742cf33b171e896f6d472[m
[33m|[m Author: Haed Hutner <mvvasilev@outlook.com>
[33m|[m Date:   Sat Mar 24 20:01:35 2018 +0200
[33m|[m 
[33m|[m     Minor change to Questions API
[33m|[m   
*   [33mcommit 218123348f7d2c790572ae264426490c0041e314[m
[34m|[m[35m\[m  Merge: cfe5e46 ca05205
[34m|[m [35m|[m Author: HaedHutner <mvvasilev@outlook.com>
[34m|[m [35m|[m Date:   Thu Mar 22 11:11:49 2018 +0200
[34m|[m [35m|[m 
[34m|[m [35m|[m     Merge pull request #8 from Atherys-Horizons/sponge-type-adapters
[34m|[m [35m|[m     
[34m|[m [35m|[m     Remove unnecessary classes
[34m|[m [35m|[m 
[34m|[m * [33mcommit ca05205c09e5de99cdfe6301b26b3c4aa63b1cee[m[33m ([m[1;31morigin/sponge-type-adapters[m[33m)[m
[34m|[m [35m|[m Author: HaedHutner <mvvasilev@outlook.com>
[34m|[m [35m|[m Date:   Tue Mar 20 13:41:34 2018 +0200
[34m|[m [35m|[m 
[34m|[m [35m|[m     Remove unnecessary classes
[34m|[m [35m|[m     Remove debug messages
[34m|[m [35m|[m   
* [35m|[m   [33mcommit cfe5e462f8fe37c7c76b8918f11c8bad9664c105[m
[36m|[m[35m\[m [35m\[m  Merge: ab9a913 056bfb2
[36m|[m [35m|[m[35m/[m  Author: HaedHutner <mvvasilev@outlook.com>
[36m|[m [35m|[m   Date:   Mon Mar 19 21:33:06 2018 +0200
[36m|[m [35m|[m   
[36m|[m [35m|[m       Merge pull request #7 from Atherys-Horizons/sponge-type-adapters
[36m|[m [35m|[m       
[36m|[m [35m|[m       Fix ConfigurateAdapter
[36m|[m [35m|[m 
[36m|[m * [33mcommit 056bfb233348a1275741be8b874828d75bda31d1[m
[36m|[m [1;31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[36m|[m [1;31m|[m Date:   Mon Mar 19 21:21:13 2018 +0200
[36m|[m [1;31m|[m 
[36m|[m [1;31m|[m     Fix ConfigurateAdapter
[36m|[m [1;31m|[m   
* [1;31m|[m   [33mcommit ab9a9134821d2937a9db200eac4504b8b427556f[m
[1;32m|[m[1;31m\[m [1;31m\[m  Merge: 1f7864d 6a4941a
[1;32m|[m [1;31m|[m[1;31m/[m  Author: HaedHutner <mvvasilev@outlook.com>
[1;32m|[m [1;31m|[m   Date:   Mon Mar 19 18:11:47 2018 +0200
[1;32m|[m [1;31m|[m   
[1;32m|[m [1;31m|[m       Merge pull request #6 from Atherys-Horizons/sponge-type-adapters
[1;32m|[m [1;31m|[m       
[1;32m|[m [1;31m|[m       Sponge type adapters
[1;32m|[m [1;31m|[m 
[1;32m|[m * [33mcommit 6a4941ac4fc3d24fdb3c1745571e66261c806c2d[m
[1;32m|[m [1;33m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;32m|[m [1;33m|[m Date:   Mon Mar 19 17:36:11 2018 +0200
[1;32m|[m [1;33m|[m 
[1;32m|[m [1;33m|[m     Fixed unintentionally protected constructors
[1;32m|[m [1;33m|[m 
[1;32m|[m * [33mcommit 00945e4b157be7275cbd4827eac1c085a31dc015[m
[1;32m|[m [1;33m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;32m|[m [1;33m|[m Date:   Mon Mar 19 17:32:54 2018 +0200
[1;32m|[m [1;33m|[m 
[1;32m|[m [1;33m|[m     Added abstract and implementation versions of a generic TypeAdapter for classes.
[1;32m|[m [1;33m|[m 
[1;32m|[m * [33mcommit b257d26230a4b249e8de2b9526d8ca49d16ea825[m
[1;32m|[m [1;33m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;32m|[m [1;33m|[m Date:   Mon Mar 19 16:36:39 2018 +0200
[1;32m|[m [1;33m|[m 
[1;32m|[m [1;33m|[m     Removed Debug messages from Adapters
[1;32m|[m [1;33m|[m     Also, unintentional Code reformat.
[1;32m|[m [1;33m|[m   
* [1;33m|[m   [33mcommit 1f7864d1d6116f6133145eed3dcc2c36746c4125[m
[1;34m|[m[1;33m\[m [1;33m\[m  Merge: 7cc1f90 b834905
[1;34m|[m [1;33m|[m[1;33m/[m  Author: HaedHutner <mvvasilev@outlook.com>
[1;34m|[m [1;33m|[m   Date:   Mon Mar 19 16:18:39 2018 +0200
[1;34m|[m [1;33m|[m   
[1;34m|[m [1;33m|[m       Merge pull request #5 from Atherys-Horizons/sponge-type-adapters
[1;34m|[m [1;33m|[m       
[1;34m|[m [1;33m|[m       Added Gson TypeAdapters for Text and ItemStackSnapshot
[1;34m|[m [1;33m|[m 
[1;34m|[m * [33mcommit b8349059b8bd587d10f8faa79d04e468b3cca5c7[m
[1;34m|[m[1;34m/[m  Author: HaedHutner <mvvasilev@outlook.com>
[1;34m|[m   Date:   Mon Mar 19 16:16:48 2018 +0200
[1;34m|[m   
[1;34m|[m       Added Gson TypeAdapters for Text and ItemStackSnapshot
[1;34m|[m 
* [33mcommit 7cc1f90f1bf8cc3cd3f53a785730d3a219529df6[m
[1;35m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;35m|[m Date:   Fri Mar 16 18:45:13 2018 +0200
[1;35m|[m 
[1;35m|[m     Relocated shadowed dependencies into "com.atherys.libs"
[1;35m|[m 
* [33mcommit ade22d08c9e3be60dec40469e1e56afc1e83d754[m
[1;35m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;35m|[m Date:   Fri Mar 16 18:10:48 2018 +0200
[1;35m|[m 
[1;35m|[m     Now shadowing LiteORM by j256 into AtherysCore
[1;35m|[m 
* [33mcommit 47abdd903c714d28c556523e95ec3e8390d20ed8[m
[1;35m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;35m|[m Date:   Fri Mar 16 18:07:12 2018 +0200
[1;35m|[m 
[1;35m|[m     Now shadowing MongoDB Java Driver into AtherysCore
[1;35m|[m 
* [33mcommit cfc27c41a868a39920fb2d4fd7502052881b7f26[m
[1;35m|[m Author: Haed Hutner <mvvasilev@outlook.com>
[1;35m|[m Date:   Mon Mar 12 14:16:16 2018 +0200
[1;35m|[m 
[1;35m|[m     Update Readme
[1;35m|[m 
* [33mcommit 9dce4eab6f13da9303161ae85be06ff4f91dc60f[m
[1;35m|[m Author: Haed Hutner <mvvasilev@outlook.com>
[1;35m|[m Date:   Mon Mar 12 10:44:34 2018 +0200
[1;35m|[m 
[1;35m|[m     Update build.gradle
[1;35m|[m 
* [33mcommit 878c725f3119d67f2ceac8515258df00d7e50625[m
[1;35m|[m Author: Haed Hutner <mvvasilev@outlook.com>
[1;35m|[m Date:   Mon Mar 12 10:40:43 2018 +0200
[1;35m|[m 
[1;35m|[m     Added License & Readme
[1;35m|[m 
* [33mcommit ebfd39a0df5e8af50e1381fa4cb33b68c002078e[m
[1;35m|[m Author: Haed Hutner <mvvasilev@outlook.com>
[1;35m|[m Date:   Sun Mar 11 13:49:58 2018 +0200
[1;35m|[m 
[1;35m|[m     Shade in MongoDB now
[1;35m|[m   
*   [33mcommit 637193674ac9d1b3730d1c635896163e7aa873f9[m
[1;36m|[m[31m\[m  Merge: d598cae a645ab7
[1;36m|[m [31m|[m Author: Miroslav Vasilev <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Sun Mar 11 11:24:53 2018 +0000
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     Merge branch 'dev' into 'master'
[1;36m|[m [31m|[m     
[1;36m|[m [31m|[m     Merge Dev Into Master
[1;36m|[m [31m|[m     
[1;36m|[m [31m|[m     See merge request AtherysSponge/AtherysCore!5
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit a645ab762077bfc77c49a7040ee8c1893fb82176[m
[1;36m|[m [31m|[m Author: Haed Hutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Sun Mar 11 13:17:57 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     Code Reformat
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit 35a3a4b541c6d2c1385e939bb15f277e7bd7fed9[m
[1;36m|[m [31m|[m Author: Haed Hutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Sat Mar 10 21:14:42 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     Added a whole bunch of documentation
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit f864d450d608985d321adf1a468c762a28b0889e[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Mon Feb 12 22:29:06 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     Refactor TextTypeAdapter
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit a76ecf35bcbe92991b1f09e28c0cacc575fa43ae[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Mon Feb 5 21:11:24 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     Upped version to 1.2.0
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit af477c8286262a9067ba5c277300d5158978b3c3[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Mon Feb 5 21:00:45 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     reversed projectile and entity source for indirect damage sources. See if this fixes it.
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit 7a7e5d746193a64cc6ca1043f445471f14d90e2d[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Mon Feb 5 20:35:48 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     added debug message
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit 3ce89f57d2b8245e9b7034019f3fb0b0bd61a2ab[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Mon Feb 5 20:28:53 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     Also register this earlier
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit b001e79c76e50066fbac8eeb60ba532bdada04ab[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Mon Feb 5 20:27:00 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     initialize AtherysDamageTypes before config for deserialization.
[1;36m|[m [31m|[m     
[1;36m|[m [31m|[m     Also move RuntimeTypeAdapterFactory util class to Core.
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit f386cf8b348774881bb048569f46a6b7dece2f87[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Mon Feb 5 20:14:52 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     Add a single default value to the damage config
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit 20908dedf5b802836b88f6b424800171c76d0d8f[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Sun Feb 4 20:04:36 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     Big push for Damage Sources. Don't even remember what I did.
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit 691e84faacf3b80d1067b2909a1bffa777ca425b[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Tue Jan 23 00:28:01 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     Removed multiple damage source support
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit e146f3a610ecd911fcb3027c1724acf8f480bb34[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Tue Jan 23 00:06:10 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     what if I reapply damage 5 times over?
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit 1faf61b4767ff46d2c6094b4b379cfe9417e7769[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Mon Jan 22 23:59:46 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     Will this work?
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit 2aaad3b8321b5ce792a34edac29262922ee1dbec[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Mon Jan 22 22:33:40 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     Lets try to crash the server again oh boy
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit 00d281807c7b192dd51c6b6a1a8bcad317adfb64[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Sun Jan 21 21:21:16 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     What about this?
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit c00bf64201e19920c5895da8b820cee2c5eb8e19[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Sun Jan 21 21:15:21 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     Try this instead
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit 0329d9c6035fd17bfd0bc87e9b83ead3c53eef35[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Sun Jan 21 18:50:24 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     Debug message added
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit c759e404016e9aab59a95723765208e8885c7781[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Sun Jan 21 18:42:29 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     indirect sources also need a proxy. Derp.
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit f5dd0aa074e172cf0858ce2cfaa9922348b8e005[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Sun Jan 21 18:32:07 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     renamed atherys damage source factory methods
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit c61da579fa3ef34dd1ada8970ff467636e7a65b0[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Sun Jan 21 18:29:37 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     Improve multiple damage sources
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit c2eec4b80208037b609a056f2bac8f06dd4c4675[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Sun Jan 21 18:02:58 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     Improve multiple damage source handler
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit 710088e292a502c256ceba386d9920766dce89cf[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Sun Jan 21 17:46:42 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     Improve multiple damage source handler
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit d32e7e257f0747e7a136a4be5dbe7de75828a6e1[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Sun Jan 21 17:14:18 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     Rename lang file to en_us.lang
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit cfe8e9ba9ec34b4fd72b32c250186a8b6d97752a[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Sun Jan 21 17:00:59 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     Revert AtherysDamageType.java
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit 3055999d7500863188076597034f14b0121ec204[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Sun Jan 21 16:59:06 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     Update lang file
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit 3a2036212187038a1a33d6c83acf7dfe4e6e2c9b[m
[1;36m|[m [31m|[m Author: Haed Hutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Sun Jan 21 13:12:12 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     Alternative for death messages
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit 534a5bee052b403b02c620028726f920b825d5ab[m
[1;36m|[m [31m|[m Author: Haed Hutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Sun Jan 21 12:57:32 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     Update lang file
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit 0f1be84cb583a4d312a88075bf58eb8b097d8b43[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Sat Jan 20 12:01:04 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     Add lang file
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit 8fb5096f861e38957f9cf0ee26fdf0a92c8200e1[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Fri Jan 19 22:08:03 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     Ooops
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit 7270a4fdf615d7f360837d76b4eb430ba3fc104f[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Fri Jan 19 22:05:02 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     Try a double damage cause
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit 650f9132c3204f065146fe061a54038b20e6a589[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Fri Jan 19 21:23:55 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     This should work now
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit ce83c3ccd0d6026b0835f65ba539f36ca1c8d38b[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Fri Jan 19 21:20:33 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     Fix maybe?
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit 4cd5d166452a7ddf2d50ae39deb6d563176dccf1[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Fri Jan 19 19:40:17 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     Try popping the last cause
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit 0ee401a391b48e934497e0cea11015f08ad6cef1[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Fri Jan 19 18:53:54 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     Register event handler >_>
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit 89a698a55e3ec9e038c157d6f88be148d5977727[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Fri Jan 19 18:50:58 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     DEbug message
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit 823fcbfacabfea0cfdc67541af48a9dee6590c53[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Fri Jan 19 18:06:51 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     Removed damage source generation from damage types
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit 17668b1855812e370d4ab29f954f6dec0bd7e4eb[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Fri Jan 19 17:50:18 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     DamageListener fix
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit f924ef72ceceaf1fff5c6a19c2d64860b08297cd[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Fri Jan 19 17:45:32 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     Fix for temp atherys damage command
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit 0ddbec8b3110219b2522d64f859e15dbd2e4533c[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Fri Jan 19 16:09:05 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     Introduced damage type distribution
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit 024fb72ee6af79f1c32d0f1b19d4e1185b4ff6da[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Thu Jan 18 22:32:09 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     Updated temp command
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit 85ba82d2133ebc700f536ae7d0f1931ce586216d[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Thu Jan 18 21:28:07 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     Up version to 1.0.5
[1;36m|[m [31m|[m     Added AtherysDamageSources
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit 585ef1cf70c331aed988734036e2921fed4fc24d[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Thu Jan 18 20:59:07 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     Up version to 1.0.4
[1;36m|[m [31m|[m     Added AtherysDamageSources
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit 280a0b745835c39bbd20f0a74805de481d19d891[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Thu Jan 18 19:32:55 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     Added Pierce and Ballistic types
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit 27c7c95d05306d6fcc8f74c3faf3687eb0422908[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Thu Jan 18 17:14:16 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     *sigh* I'm a moron
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit d64e5a3a093c1cd9e07fda58e38c7ae4fa7f1f58[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Thu Jan 18 17:10:34 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     Update temp command
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit c607f472e4378c2d0bd85c30e091852e3b67f85d[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Thu Jan 18 17:07:52 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     Changed ids to get checked for arguments
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit db6508eb621e6e21adc3bb74fc1c5cac0bb83674[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Thu Jan 18 17:02:45 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     Logger message for temp command
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit 37f747610271d7a42b316dd2164f25d9f89d17f2[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Thu Jan 18 16:55:10 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     initialize types
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit de5ed58a55e167bfa722590eef047b1b1633aff9[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Thu Jan 18 16:54:00 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     Don't need embedded classes
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit 032ceddfc5f6f05fc1a7cae42a41380e6ecaa53b[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Thu Jan 18 16:45:39 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     Custom DamageTypes
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit 75b566bd5e6f50a11c90887157950803a87f5f65[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Wed Jan 17 17:28:37 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     Up version to 1.0.3
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit c5a12e423e6a73ad33f136c5611aa04bda92b1ae[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Wed Jan 17 17:23:59 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     Fixed PartyManager
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit d5d9a3b3b10a8086785a66e6bf668aeeca29e86d[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Wed Jan 17 17:21:53 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     Update to AMDM
[1;36m|[m [31m|[m 
[1;36m|[m * [33mcommit 2935eae9dd7c47b724ae6bd732065459dab944fc[m
[1;36m|[m [31m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [31m|[m Date:   Wed Jan 17 16:40:22 2018 +0200
[1;36m|[m [31m|[m 
[1;36m|[m [31m|[m     Fix for config file creation
[1;36m|[m [31m|[m   
[1;36m|[m *   [33mcommit 8dacc3434db16fd08d05c982bc2086d821720116[m
[1;36m|[m [32m|[m[33m\[m  Merge: b4bc2a6 f8bff66
[1;36m|[m [32m|[m [33m|[m Author: Miroslav Vasilev <mvvasilev@outlook.com>
[1;36m|[m [32m|[m [33m|[m Date:   Wed Jan 17 14:35:32 2018 +0000
[1;36m|[m [32m|[m [33m|[m 
[1;36m|[m [32m|[m [33m|[m     Merge branch 'feature/abstract-mongo' into 'dev'
[1;36m|[m [32m|[m [33m|[m     
[1;36m|[m [32m|[m [33m|[m     Feature/abstract mongo
[1;36m|[m [32m|[m [33m|[m     
[1;36m|[m [32m|[m [33m|[m     See merge request AtherysSponge/AtherysCore!4
[1;36m|[m [32m|[m [33m|[m 
[1;36m|[m [32m|[m * [33mcommit f8bff66941519f767a76fb2bac451a13269714da[m
[1;36m|[m [32m|[m [33m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [32m|[m [33m|[m Date:   Wed Jan 17 16:32:59 2018 +0200
[1;36m|[m [32m|[m [33m|[m 
[1;36m|[m [32m|[m [33m|[m     Now uses Sponge 7.1.0
[1;36m|[m [32m|[m [33m|[m 
[1;36m|[m [32m|[m * [33mcommit 5b4e9cde06cd2b00c2db5f857c1480d36407614f[m
[1;36m|[m [32m|[m [33m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [32m|[m [33m|[m Date:   Wed Jan 17 16:32:06 2018 +0200
[1;36m|[m [32m|[m [33m|[m 
[1;36m|[m [32m|[m [33m|[m     Better PluginConfig file creation exception throwing
[1;36m|[m [32m|[m [33m|[m   
[1;36m|[m [32m|[m *   [33mcommit 5dad645641ddf0a870d32374172331164090becd[m
[1;36m|[m [32m|[m [34m|[m[35m\[m  Merge: 452add9 4e714da
[1;36m|[m [32m|[m [34m|[m [35m|[m Author: HaedHutner <mvvasilev@outlook.com>
[1;36m|[m [32m|[m [34m|[m [35m|[m Date:   Mon Jan 15 22:30:04 2018 +0200
[1;36m|[m [32m|[m [34m|[m [35m|[m 
[1;36m|[m [32m|[m [34m|[m [35m|[m     Merge branch 'feature/abstract-mongo' of gitlab.com:AtherysSponge/AtherysCore into feature/abstract-mongo
