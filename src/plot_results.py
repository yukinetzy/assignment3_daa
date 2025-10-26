import pandas as pd
import matplotlib.pyplot as plt

sizes = ["small, "medium", "large", "extralarge"]
data = {}

for size in sizes:
    df = pd.read_csv(f"src/main/resources/output/results_{size}.csv")
    data[size] = df


plt.figure(figsize=(10,6))
for size, df in data.items():
    plt.plot(df['Vertices'], df['PrimTime(ms)'], 'o-', label=f'Prim ({size})')
    plt.plot(df['Vertices'], df['KruskalTime(ms)'], 'x--', label=f'Kruskal ({size})')

plt.xlabel('Number of Vertices')
plt.ylabel('Execution Time (ms)')
plt.title('Prim vs Kruskal Execution Time by Graph Size')
plt.legend()
plt.grid(True)
plt.tight_layout()
plt.savefig("mst_comparison_time.png", dpi=300)
plt.show()
